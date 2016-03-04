// Register events.
$("document").ready(function() {
    $("#loginBtn").bind("click", onLoginBtnClick);
    $("#userName").change(clearErrorMsg);
    $("#password").change(clearErrorMsg);
})

// Executes when login button clicked.
function onLoginBtnClick() {
    var userName = $("#userName").val();
    var password = $("#password").val();
    if (validateLoginInfo(userName, password)) {
        submitLoginInfo();
    }
}

// Validates login information.
function validateLoginInfo(userName, password) {
    if (userName == "" && password == "") {
        $("#errorMsg").text("请输入用户名和密码");
        return false;
    } else if (userName == "") {
        $("#errorMsg").text("请输入用户名");
        return false;
    } else if (password == "") {
        $("#errorMsg").text("请输入密码");
        return false;
    }
    return true;
}

// Submits information of login to server, and handles response.
function submitLoginInfo() {
    var requestUrl = getRequestUrl(REQUEST_URL.loginUrl);
    $.ajax({
        url : requestUrl,
        type : "POST",
        dataType : "json",
        data : $("#loginForm").serialize(),
        success : function(data) {
            handleResponseObject(data, function() {
                switch (data.message) {
                    case 1:
                        window.location = "./sysadmin";
                        break;
                    case 2:
                        window.location = "./contentadmin";
                        break;
                    case 3:
                        window.location = "./teacher";
                        break;
                    case 4:
                        window.location = "./student";
                        break;
                }
            }, function(errors) {
                $("#errorMsg").text(
                        errors["userName"] + ";" + errors["password"]);
            }, function(message) {
                $("#errorMsg").text(message);
            })
        },
        error : function() {
            alert("网络异常");
        }
    })
}

// Clears error message which showing page.
function clearErrorMsg() {
    $("#errorMsg").text("");
}
