$("document").ready(function() {
    init();
    initUserInfo();
})

function initUserInfo() {
    $.ajax({
        url : getRequestUrl(REQUEST_URL.userInfo),
        type : "GET",
        dataType : "json",
        success : function(currentUser) {
            $("#no").text(currentUser.id);
            $("#userName").text(currentUser.userName);
            $("#chineseName").text(currentUser.chineseName);
            $("#gender").text(getGender(currentUser.gender));
            $("#role").text(getRole(currentUser.roleType));
            $("#phone").text(currentUser.phoneNumber);
            $("#email").text(currentUser.email);
        },
        error : function() {
            alert("网络异常");
        }
    });
}