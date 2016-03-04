$("document").ready(function() {
    init();
    initUserDetail();
    $("#submit").click(function() {
        if ($("#form").smkValidate()) {
            if ($.smkEqualPass('#newPwd', '#repeatPwd')) {
                updateUserDetail();
            }
        }
    });
    $("#reset").click(function() {
        initUserDetail();
    });
})

function initUserDetail() {
    var userId = window.sessionStorage.getItem(sessionUserId);
    if (!userId) {
        showFailedMessage("数据异常请返回列表页面重新进入");
        return;
    }
    
    $.ajax({
        url : getRequestUrl(REQUEST_URL.getUser) + "?id=" + userId,
        type : "GET",
        dataType : "json",
        success : function(user) {
            $("#id").text(user.id);
            $("#userName").text(user.userName);
            $("#chineseName").val(user.chineseName);
            $("#gender").val(user.gender);
            $("#role").val(user.roleType);
            $("#phone").val(user.phoneNumber);
            $("#email").val(user.email);
        },
        error : function() {
            showFailedMessage("网络异常");
        }
    });
}

function getUserInfo() {
    var user = {};
    user.id = $("#id").text();
    user.userName = $("#userName").text();
    user.password = $("#newPwd").val();
    user.chineseName = $("#chineseName").val();
    user.gender = $("#gender").val();
    user.roleType = $("#roleType").val();
    user.phoneNumber = $("#phone").val();
    user.email = $("#email").val();
    return user;
}

function updateUserDetail() {
    var newPwd = $("#newPwd").val();
    var user = getUserInfo();
    $.ajax({
        url : getRequestUrl(REQUEST_URL.updateUser),
        type : "POST",
        contentType : 'application/json',
        dataType : "json",
        asyn : false,
        data : JSON.stringify(user),
        success : function(data) {
            handleResponseObject(data, function() {
                window.location = "/OnlineExamSystem/page/sysadmin/user_detail";
            }, function(errors) {
                showFailedMessage(errors["password"] || '');
                showFailedMessage(errors["chineseName"] || '');
                showFailedMessage(errors["roleType"] || '');
                showFailedMessage(errors["gender"] || '');
                showFailedMessage(errors["phoneNumber"] || '');
                showFailedMessage(errors["email"] || '');
            })
        },
        error : function() {
            showFailedMessage("网络异常");
        }
    });
}