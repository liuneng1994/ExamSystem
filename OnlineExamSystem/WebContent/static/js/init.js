function init() {
    $.ajax({
        url : getRequestUrl(REQUEST_URL.userInfo),
        type : "GET",
        dataType : "json",
        success : function(data) {
            $("#currentUser").text(data.userName);
        },
        error : function() {
            alert("网络异常");
        }
    });
}
