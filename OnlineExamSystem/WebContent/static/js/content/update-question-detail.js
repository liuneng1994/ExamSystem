$("document").ready(function() {
    init();
    initQuestionDetail();
    $("#submit").click(function() {
        if ($("#form").smkValidate()) {
                updateQuestionDetail();
        }
    });
    $("#reset").click(function() {
        initQuestionDetail();
    });
})

function initQuestionDetail() {
    var questionId = window.sessionStorage.getItem(sessionQuestionId);
    if (!questionId) {
        showFailedMessage("数据异常请返回列表页面重新进入");
        return;
    }
    
    $.ajax({
        url : getRequestUrl(REQUEST_URL.getQuestion) + "?id=" + questionId,
        type : "GET",
        dataType : "json",
        success : function(question) {
            $("#id").text(question.id);
            $("#question").val(question.question);
            $("#choiceA").val(question.choiceA);
            $("#choiceB").val(question.choiceB);
            $("#choiceC").val(question.choiceC);
            $("#choiceD").val(question.choiceD);
            $("#answer").val(question.answer);
            $("#description").val(question.description);
        },
        error : function() {
            showFailedMessage("网络异常");
        }
    });
}

function getQuestionInfo() {
    var question = {};
    question.id = $("#id").text();
    question.question = $("#question").val();
    question.choiceA = $("#choiceA").val();
    question.choiceB = $("#choiceB").val();
    question.choiceC = $("#choiceC").val();
    question.choiceD = $("#choiceD").val();
    question.answer = $("#answer").val();
    question.description = $("#description").val();
    return question;
}

function updateQuestionDetail() {
    var question = getQuestionInfo();
    $.ajax({
        url : getRequestUrl(REQUEST_URL.updateQuestion),
        type : "POST",
        contentType : 'application/json',
        dataType : "json",
        asyn : false,
        data : JSON.stringify(question),
        success : function(data) {
            handleResponseObject(data, function() {
                window.location = "/OnlineExamSystem/page/contentadmin/question_detail";
            }, function(errors) {
                showFailedMessage(errors["id"] || '');
                showFailedMessage(errors["question"] || '');
                showFailedMessage(errors["choiceA"] || '');
                showFailedMessage(errors["choiceB"] || '');
                showFailedMessage(errors["choiceC"] || '');
                showFailedMessage(errors["choiceD"] || '');
                showFailedMessage(errors["answer"] || '');
                showFailedMessage(errors["description"] || '');
            })
        },
        error : function() {
            showFailedMessage("网络异常");
        }
    });
}