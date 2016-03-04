var pagination = {};
pagination.offset = 0;
pagination.perpageCount = 5;
pagination.sortColumn = 'id';
pagination.keyword = '';
pagination.sortMethod = 1;
pagination.totalPage = 1;
pagination.currentPage = 1;

var row = '<tr>'
        + '<td></td>'
        + '<td></td>'
        + '<td style="color:blue;cursor:pointer;" onclick="onClickUsername(event.target)"></td>'
        + '<td></td>' + '<td></td>' + '<td></td>' + '<td></td>' + '<td></td>'
        + '<td></td>' + '</tr>';

$("document").ready(function() {
    init();
    refreshUserList();
    $("#goBtn").click(onClickGo);
    $("#firstBtn").click(firstPage);
    $("#preBtn").click(prePage);
    $("#nextBtn").click(nextPage);
    $("#lastBtn").click(lastPage);
    $("#idHeader").click(function() {
        onClickHeader("id")
    });
    $("#userNameHeader").click(function() {
        onClickHeader("userName")
    });
    $("#roleTypeHeader").click(function() {
        onClickHeader("roleType")
    });
    $("#perPageCount").change(onPerpageChange);
    $("th input[type='checkbox']").change(toggleDeleteStatus);
    $("#searchBtn").click(onClickSearch);
    $("#deleteBtn").click(onClickDeleteBtn);
})

function refreshUserList() {
    $.ajax({
        url : getRequestUrl(REQUEST_URL.queryUser),
        type : 'POST',
        contentType : 'application/json',
        dataType : 'json',
        data : JSON.stringify(pagination),
        success : function(data) {
            refreshListItem(data.list);
            pagination.totalPage = data.pagination.totalPageCount;
            refreshStatus();
        },
        error : function() {
            showFailedMessage('网络异常');
        }
    });
}

function refreshStatus() {
    $('#pageCount').text(pagination.currentPage + '/' + pagination.totalPage);
    $("#firstBtn").removeClass("disabled");
    $("#preBtn").removeClass("disabled");
    $("#nextBtn").removeClass("disabled");
    $("#lastBtn").removeClass("disabled");
    if (pagination.currentPage == 1) {
        $("#firstBtn").addClass("disabled");
        $("#preBtn").addClass("disabled");
    }

    if (pagination.totalPage == pagination.currentPage) {
        $("#lastBtn").addClass("disabled");
        $("#nextBtn").addClass("disabled");
    }
    $("#currentPage").val(pagination.currentPage);
    $("#currentPage").attr("max", pagination.totalPage);
    $("#keyword").val(pagination.keyword);
    $("th input[type=checkbox]").prop("checked", false);
}

function setInfoToGrid($grid, content, maxLength) {
    if (content.length > maxLength) {
        $grid.text(content.substr(0, maxLength) + '...');
        $grid.attr("title", content);
    } else {
        $grid.text(content);
    }
}

function refreshListItem(list) {
    $("tbody").empty();
    for (var i = 0; i < list.length; i++) {
        var $row = $(row);
        setInfoToGrid($row.children().eq(0), i + 1, 3);
        setInfoToGrid($row.children().eq(1), list[i].id, 5);
        setInfoToGrid($row.children().eq(2), list[i].userName, 10);
        setInfoToGrid($row.children().eq(3), getRole(list[i].roleType), 5);
        setInfoToGrid($row.children().eq(4), getGender(list[i].gender), 2);
        setInfoToGrid($row.children().eq(5), list[i].chineseName, 6);
        setInfoToGrid($row.children().eq(6), list[i].phoneNumber, 10);
        setInfoToGrid($row.children().eq(7), list[i].email, 10);
        $row.children().eq(8).append($('<input type="checkbox" />'));
        $("table").append($row);
    }

    for (var i = list.length; i < pagination.perpageCount; i++) {
        var $row = $(row);
        $row.children().eq(8).append(
                $('<input type="checkbox" style="visibility:hidden;" />'));
        $("table").append($row);
    }
}

function resetHeader() {
    $("#idHeader").children("span").attr("class", "");
    $("#userNameHeader").children("span").attr("class", "");
    $("#roleTypeHeader").children("span").attr("class", "");
}

function onClickHeader(column) {
    resetHeader();
    pagination.sortMethod = (pagination.sortMethod + 1) % 2;
    pagination.sortColumn = column;
    if (pagination.sortMethod == 1) {
        $("#"+ column +"Header span").attr("class", "glyphicon glyphicon-chevron-down");
    } else if (pagination.sortMethod == 0) {
        $("#"+ column +"Header span").attr("class", "glyphicon glyphicon-chevron-up");
    }
    refreshUserList();
}

function onPerpageChange() {
    pagination.perpageCount = $('#perPageCount').val();
    firstPage();
}

function onClickSearch() {
    pagination.keyword = $("#keyword").val();
    refreshUserList();
}

function nextPage() {
    pagination.currentPage++;
    forwardTarget(pagination.currentPage);
}

function prePage() {
    pagination.currentPage--;
    forwardTarget(pagination.currentPage);
}

function firstPage() {
    forwardTarget(1)
}

function lastPage() {
    forwardTarget(pagination.totalPage)
}

function forwardTarget(target) {
    if (target > pagination.totalPage) {
        target = pagination.totalPage;
    }
    if (target < 1) {
        target = 1;
    }
    pagination.currentPage = target;
    pagination.offset = (target - 1) * pagination.perpageCount;
    refreshUserList();
}

function onClickGo() {
    var target = $("#currentPage").val();
    if (target == "") {
        showAlertMessage("请输入正确的页码");
        return;
    }
    forwardTarget(target);
}

function toggleDeleteStatus() {
    $("td input[visibility!=hidden]").prop("checked",
            $("th input[type=checkbox]").prop("checked"));
}

function getDeletedItemIds() {
    var ids = new Array(15);
    for (var i = 0, j = 0; i < pagination.perpageCount; i++) {
        if ($("tbody tr").eq(i).children().eq(0).text() == '') {
            continue;
        }

        if ($("tbody tr td input[type=checkbox]").eq(i).prop("checked")) {
            ids[j] = parseInt($("tbody tr").eq(i).children().eq(1).text());
            j++;
        }
    }

    var length = 0;
    for (var i = 0; i < 15; i++) {
        if (ids[i] != null) {
            length++;
        }
    }

    var result = new Array(length);
    for (var i = 0, j = 0; i < 15; i++) {
        if (ids[i] != null) {
            result[j] = ids[i];
            j++;
        }
    }
    return result;
}

function onClickDeleteBtn() {
    if (getDeletedItemIds().length == 0) {
        showAlertMessage("请选择需要删除的用户");
        return;
    }
    showConfirmMessage('确认删除', "是否确认要删除以下id的用户"
            + JSON.stringify(getDeletedItemIds()), function() {
        $.ajax({
            url : getRequestUrl(REQUEST_URL.deleteUsers),
            type : 'POST',
            contentType : 'application/json',
            dataType : 'json',
            data : JSON.stringify(getDeletedItemIds()),
            success : function(data) {
                showSuccessMessage(data.message);
                refreshUserList();
            },
            error : function() {
                showAlertMessage("网络异常，无法删除");
            }
        });
    });
}

function onClickUsername(target) {
    window.sessionStorage.setItem(sessionUserId, $(target).siblings().eq(1)
            .text());
    window.location = "/OnlineExamSystem/page/sysadmin/user_detail";
}
