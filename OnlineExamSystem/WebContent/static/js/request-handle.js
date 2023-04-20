function handleResponseObject(data, successMethod, errorMethod, failMethod) {
    if (data.code == statusCode.successCode) {
        successMethod();
    } else {
        if (typeof data.message === 'string') {
            if (failMethod) {
                failMethod(data.message);
            } else {
                showAlertMessage(data.message);
            }
        } else {
            errorMethod(data.message);
        }
    }
}

function showConfirmMessage(title, content, SubmitMethod) {
    showMessageDialog(title, content, SubmitMethod);
}

function showMessageDialog(title, content, SubmitMethod) {
    $("#myModalLabel").text(title);
    $("#messageDlg .modal-content .modal-body").text(content);
    $("#messageDlgOkBtn").click(function() {
        SubmitMethod();
        $("#messageDlg").modal("hide");
    });
    $("#messageDlg").modal("show");
}

function showAlertMessage(message) {
    if (message) {
        $.smkAlert({
            text : message,
            type : 'warning'
        });
    }
}

function showSuccessMessage(message) {
    if (message) {
        $.smkAlert({
            text : message,
            type : 'success'
        });
    }
}

function showFailedMessage(message) {
    if (message) {
        $.smkAlert({
            text : message,
            type : 'danger'
        });
    }
}
