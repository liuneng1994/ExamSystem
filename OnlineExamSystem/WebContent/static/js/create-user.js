$("document").ready(function() {
    init();
    $("#userFormSubmit").click(function() {
        if ($("#userForm").smkValidate()) {
            doRegister();
        }
    });
    $("#reset").click(function() {
       $("#userForm").smkClear(); 
    });
})

function doRegister() {
    var data = getRegisterInfo();
    $.ajax({
        url : getRequestUrl(REQUEST_URL.createUser),
        type : 'POST',
        contentType : 'application/json',
        dataType : 'json',
        async : false,
        data : JSON.stringify(data),
        success : function(data) {
            handleResponseObject(data, function() {
                showSuccessMessage("创建用户成功:ID为" + data.message);
            }, function(errors) {
                showFailedMessage(errors["userName"] || '');
                showFailedMessage(errors["chineseName"] || '');
                showFailedMessage(errors["roleType"] || '');
                showFailedMessage(errors["gender"] || '');
                showFailedMessage(errors["phoneNumber"] || '');
                showFailedMessage(errors["email"] || '');
            });
        },
        error : function() {
            showFailedMessage("网络错误");
        }
    });
}
/**
 * Gets user information object.
 * 
 * @returns user oject
 */
function getRegisterInfo() {
    var user = {};
    user.userName = $("#userName").val();
    user.chineseName = $("#chineseName").val();
    user.gender = $("#gender").val();
    user.roleType = $("#roleType").val();
    user.phoneNumber = $("#phone").val();
    user.email = $("#email").val();
    return user;
}
