package com.adrian.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.*;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.adrian.command.LoginObject;
import com.adrian.common.Constants;
import com.adrian.common.ResponseMessage;
import com.adrian.model.User;
import com.adrian.service.UserService;
import com.adrian.type.RequestResultType;
import com.adrian.util.LoginUsers;
import com.adrian.util.StringUtil;
import com.adrian.validate.util.ValidateUtils;

@Controller
@RequestMapping("/request")
public class LoginController extends BaseController {
    @Resource(name = "userService")
    private UserService userService;

    /**
     * Processes user login request, and if user set remembered to true, adds
     * login information to cookies.
     *
     * @param userName
     *            user name
     * @param password
     *            password
     * @param remembered
     *            flag of auto login
     * @param response
     *            http response object
     * @return result message
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doLogin(@Valid LoginObject loginObject, BindingResult bindingResult,
            HttpServletResponse response) {
        ResponseMessage message = new ResponseMessage();
        if (bindingResult.hasErrors()) {
            message.setCode(RequestResultType.FAILED);
            message.setMessage(ValidateUtils.transformErrorsToMap(bindingResult.getFieldErrors()));
        }
        loginObject.setPassword(StringUtil.EncoderByMd5(loginObject.getPassword()));
        User user = this.userService.login(loginObject.getUserName(), loginObject.getPassword());
        if (user != null) {
            if (!LoginUsers.contains(loginObject.getUserName())) {
                message.setCode(RequestResultType.SUCCESS);
                message.setMessage(user.getRoleType());
                this.setSessionAttribute(Constants.SESSION_USER, user);
                LoginUsers.addUser(user.getUserName());
                if (loginObject.isRemembered()) {
                    Cookie nameCookie = new Cookie(Constants.COOKIE_ATTRIBUTE_USERNAME, loginObject.getUserName());
                    nameCookie.setMaxAge(31 * 24 * 60 * 60);
                    nameCookie.setPath("/");
                    Cookie pwdCookie = new Cookie(Constants.COOKIE_ATTRIBUTE_PASSWORD, loginObject.getPassword());
                    pwdCookie.setMaxAge(31 * 24 * 60 * 60);
                    pwdCookie.setPath("/");
                    response.addCookie(nameCookie);
                    response.addCookie(pwdCookie);
                }
            } else {
                message.setCode(RequestResultType.FAILED);
                message.setMessage("该用户已登录");
            }
        } else {
            message.setCode(RequestResultType.FAILED);
            message.setMessage("用户名或密码错误");
        }
        return message;
    }

    /**
     * Processes logout request. It will clear cookies.
     *
     * @param response
     *            http response object
     * @return login page path
     */
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String doLogout(HttpServletRequest request, HttpServletResponse response) {
        Set<String> set = new HashSet<>(
                Arrays.asList(Constants.COOKIE_ATTRIBUTE_USERNAME, Constants.COOKIE_ATTRIBUTE_PASSWORD));
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (set.contains(cookie.getName())) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        LoginUsers.deleteUser(((User) getSessionAttribute(Constants.SESSION_USER)).getUserName());
        this.InvalidateSession();
        return Constants.REDIRECT_PREFIX + Constants.PAGE_LOGIN;
    }
}
