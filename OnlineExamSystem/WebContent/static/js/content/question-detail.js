$("document").ready(function() {
    init();
    initQuestionDetail();
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
            $("#question").text(question.question);
            $("#choiceA").text(question.choiceA);
            $("#choiceB").text(question.choiceB);
            $("#choiceC").text(question.choiceC);
            $("#choiceD").text(question.choiceD);
            $("#answer").text(getQuestionAnswer(question.answer));
            $("#description").text(question.description);
        },
        error : function() {
            showFailedMessage("网络异常");
        }
    });
}