$("document").ready(function() {
    init();
    initInfo();
    $("#submit").click(function() {
        if ($("#form").smkValidate()) {
            updateInfo();
        }
    });
    $("#reset").click(function() {
        initInfo();
    });
})
var currentUserInfo = {};

function initInfo() {
    $.ajax({
        url : getRequestUrl(REQUEST_URL.userInfo),
        type : "GET",
        dataType : "json",
        success : function(currentUser) {
            currentUserInfo = currentUser;
            $("#userName").text(currentUser.userName);
            $("#chineseName").val(currentUser.chineseName);
            $("#gender").val(currentUser.gender);
            $("#role").val(currentUser.roleType);
            $("#phone").val(currentUser.phoneNumber);
            $("#email").val(currentUser.email);
        },
        error : function() {
            showFailedMessage("网络异常");
        }
    });
}

function updateInfo() {
    var user = getUserInfo();
    $.ajax({
        url : getRequestUrl(REQUEST_URL.updateBaseInfo),
        type : "POST",
        contentType : 'application/json',
        dataType : "json",
        asyn : false,
        data : JSON.stringify(user),
        success : function(data) {
            handleResponseObject(data, function() {
                window.location = "/OnlineExamSystem/page/sysadmin/person_info";
            }, function(errors) {
                showFailedMessage(errors["chineseName"] || '');
                showFailedMessage(errors["gender"] || '');
                showFailedMessage(errors["phoneNumber"] || '');
                showFailedMessage(errors["email"] || '');
            });
        },
        error : function() {
            showFailedMessage("网络异常");
        }
    });
}

function onClickReset() {
    initInfo(); 
}

function getUserInfo() {
    var user = {};
    user.id = currentUserInfo.id;
    user.userName = currentUserInfo.userName;
    user.chineseName = $("#chineseName").val();
    user.gender = $("#gender").val();
    user.phoneNumber = $("#phone").val();
    user.email = $("#email").val();
    return user;
}