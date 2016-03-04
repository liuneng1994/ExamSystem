$("document").ready(function() {
    init();
    $("#submit").click(function() {
        if ($("#form").smkValidate()) {
            doRegister();
        }
    });
    $("#reset").click(function() {
       $("#form").smkClear(); 
    });
})

function doRegister() {
    var data = getQuestionInfo();
    $("#submit").addClass("disabled");
    $("#submit").text("请求中");
    $.ajax({
        url : getRequestUrl(REQUEST_URL.addQuestion),
        type : 'POST',
        contentType : 'application/json',
        dataType : 'json',
        data : JSON.stringify(data),
        success : function(data) {
            handleResponseObject(data, function() {
                showSuccessMessage("创建试题成功:ID为" + data.message);
                $("#submit").removeClass("disabled");
                $("#submit").text("创建");
            }, function(errors) {
                showFailedMessage(errors["question"] || '');
                showFailedMessage(errors["choiceA"] || '');
                showFailedMessage(errors["choiceB"] || '');
                showFailedMessage(errors["choiceC"] || '');
                showFailedMessage(errors["choiceD"] || '');
                showFailedMessage(errors["answer"] || '');
                showFailedMessage(errors["desription"] || '');
                $("#submit").removeClass("disabled");
                $("#submit").text("创建");
            });
        },
        error : function() {
            showFailedMessage("网络错误");
            $("#submit").removeClass("disabled");
            $("#submit").text("创建");
        }
    });
}

function getQuestionInfo() {
    var question = {};
    question.question = $("#question").val();
    question.choiceA = $("#choiceA").val();
    question.choiceB = $("#choiceB").val();
    question.choiceC = $("#choiceC").val();
    question.choiceD = $("#choiceD").val();
    question.answer = $("#answer").val();
    question.description = $("#description").val();
    return question;
}