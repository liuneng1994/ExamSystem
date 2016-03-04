$("document").ready(function() {
    init();
    $("#formSubmit").click(function() {
        if ($("#passwordForm").smkValidate()) {
            if ($.smkEqualPass('#newPwd', '#repeatPwd')) {
                updatePassword();
            }
        }
    });
})

function updatePassword() {
    var oldPwd = $("#oldPwd").val();
    var newPwd = $("#newPwd").val();
    
    var data = "oldPwd=" + oldPwd + "&" + "newPwd=" + newPwd;
    $.ajax({
        url : getRequestUrl(REQUEST_URL.updatePassword),
        type : "POST",
        dataType : "json",
        data : data,
        success : function(data) {
            switch (data.code) {
                case "SUCCESS" :
                    showSuccessMessage(data.message);
                    break;
                case "FAILED" :
                    showFailedMessage(data.message);
                    break;
            }
        },
        error : function() {
            alert("网络异常");
        }
    });
}

function clearErrorMessage() {
    $("#oldPwdError").text("");
    $("#newPwdError").text("");
    $("#repeatPwdError").text("");
}