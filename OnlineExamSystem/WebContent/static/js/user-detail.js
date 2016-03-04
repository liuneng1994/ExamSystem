$("document").ready(function() {
    init();
    initUserDetail();
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
            $("#chineseName").text(user.chineseName);
            $("#gender").text(getGender(user.gender));
            $("#role").text(getRole(user.roleType));
            $("#phone").text(user.phoneNumber);
            $("#email").text(user.email);
        },
        error : function() {
            showFailedMessage("网络异常");
        }
    });
}