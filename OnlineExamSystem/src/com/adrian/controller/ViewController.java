package com.adrian.controller;

import static com.adrian.common.Constants.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.adrian.common.Constants;
import com.adrian.model.User;
import com.adrian.service.UserService;
import com.adrian.util.LoginUsers;
import com.adrian.util.StringUtil;

@Controller
@RequestMapping(path = "/page")
public class ViewController extends BaseController {

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String showLoginPage(
            @CookieValue(name = Constants.COOKIE_ATTRIBUTE_USERNAME, defaultValue = "") String userName,
            @CookieValue(name = Constants.COOKIE_ATTRIBUTE_PASSWORD, defaultValue = "") String password,
            @RequestParam(name = "fromUrl", defaultValue = "") String fromUrl) {
        if (!StringUtil.stringIsNullOrEmpty(userName) && !StringUtil.stringIsNullOrEmpty(password)) {
            User user = this.userService.login(userName, password);
            if (user != null) {
                this.setSessionAttribute(Constants.SESSION_USER, user);
                LoginUsers.addUser(user.getUserName());
                String path = StringUtil.STRING_EMPTY;
                if (!StringUtil.stringIsNullOrEmpty(fromUrl)) {
                    return Constants.REDIRECT_PREFIX + fromUrl.replace(PROJECT_ROOT, "");
                } else {
                    switch (user.getRoleType()) {
                        case 1:
                            path = PAGE_SYSADMIN;
                            break;
                        case 2:
                            path = PAGE_CONTENTADMIN;
                            break;
                        case 3:
                            path = PAGE_TEACHER;
                            break;
                        case 4:
                            path = PAGE_STUDENT;
                            break;
                        default:
                            break;
                    }
                }
                return Constants.REDIRECT_PREFIX + path;
            }
        }
        return HTML_LOGIN;
    }

    /*
     * @RequestMapping(path = "/sysadmin", method = RequestMethod.GET) public
     * String showSysadminPage() { return HTML_SYSADMIN; }
     *
     * @RequestMapping(path = "/sysadmin/create_user", method =
     * RequestMethod.GET) public String showCreateUserPage() { return
     * HTML_CREATE_USER; }
     *
     * @RequestMapping(path = "/sysadmin/person_info", method =
     * RequestMethod.GET) public String showPersonInfo() { return
     * HTML_PERSON_INFO; }
     *
     * @RequestMapping(path = "/sysadmin/update_current_other_info", method =
     * RequestMethod.GET) public String showUpdateCurrentOtherInfo() { return
     * HTML_UPDATE_CURRENT_OTHER_INFO; }
     *
     * @RequestMapping(path = "/sysadmin/update_current_password", method =
     * RequestMethod.GET) public String showUpdateCurrentPassword() { return
     * HTML_UPDATE_CURRENT_PASSWORD; }
     *
     * @RequestMapping(path = "/sysadmin/user_detail", method =
     * RequestMethod.GET) public String showUserDetail() { return
     * HTML_SYS_USER_DETAIL; }
     *
     * @RequestMapping(path = "/sysadmin/update_user_detail", method =
     * RequestMethod.GET) public String showUpdateUserDetail() { return
     * HTML_UPDATE_USER_DETAIL; }
     */
}
