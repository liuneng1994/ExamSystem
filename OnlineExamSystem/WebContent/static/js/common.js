var SERVER_URL = "/OnlineExamSystem";
var REQUEST_URL = {};
REQUEST_URL.UrlSuffix = "/request"
REQUEST_URL.loginUrl = "/login";
REQUEST_URL.createUser = "/user/create_user";
REQUEST_URL.deleteUsers = "/user/delete_user";
REQUEST_URL.queryUser = "/user/query_user";
REQUEST_URL.userInfo = "/user/current_user";
REQUEST_URL.updatePassword = "/user/update_password";
REQUEST_URL.updateBaseInfo = "/user/update_base_info";
REQUEST_URL.updateUser  = "/user/update_user";
REQUEST_URL.getUser  = "/user/get_user";

REQUEST_URL.addQuestion = "/question/add";
REQUEST_URL.updateQuestion = "/question/update";
REQUEST_URL.deleteQuestions = "/question/delete";
REQUEST_URL.getQuestion = "/question/get";
REQUEST_URL.queryQuestion = "/question/get_questions";

// Status code of request, one indicates successed, zero indicates failed.
var statusCode = {};
statusCode.successCode = 'SUCCESS';
statusCode.failCode = 'FAILED';

var sessionUserId = "userDetailId";
var sessionQuestionId = "questionDetailId"

function getRequestUrl(url) {
    return SERVER_URL + REQUEST_URL.UrlSuffix + url;
}

function getRole(role) {
    switch(role) {
        case 1:
            return '系统管理员';
        case 2:
            return '题库管理员';
        case 3:
            return '教师';
        case 4: 
            return '学生';
        default:
            return role;
    }
}

function getGender(gender) {
    switch(gender) {
        case 0:
            return '男';
        case 1:
            return '女';
        default:
            return gender;
    }
}

function getQuestionAnswer(answer) {
    switch(answer) {
        case 1:
            return 'A';
        case 2:
            return 'B';
        case 3:
            return 'C';
        case 4:
            return 'D';
    }
    return answer;
}