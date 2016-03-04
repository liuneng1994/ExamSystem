package com.adrian.controller;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.adrian.command.BasePagination;
import com.adrian.common.Constants;
import com.adrian.common.ResponseMessage;
import com.adrian.model.User;
import com.adrian.service.UserService;
import com.adrian.type.RequestResultType;
import com.adrian.util.StringUtil;
import com.adrian.validate.group.*;
import com.adrian.validate.util.ValidateUtils;

@Controller
@RequestMapping("/request/user")
public class UserController extends BaseController {
    @Resource(name = "userService")
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/current_user", method = RequestMethod.GET)
    @ResponseBody
    public User getCurrentUserInfo() {
        return (User) getSessionAttribute(Constants.SESSION_USER);
    }

    /**
     * Creates new user.
     *
     * @param user
     *            user information
     * @return result message
     */
    @RequestMapping(path = "/create_user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage createUser(@RequestBody @Validated(ValidateCreate.class) User user,
            BindingResult bindingResult) {
        ResponseMessage message = new ResponseMessage();
        if (bindingResult.hasErrors()) {
            message.setCode(RequestResultType.FAILED);
            message.setMessage(ValidateUtils.transformErrorsToMap(bindingResult.getFieldErrors()));
            return message;
        }
        int result = userService.addUser(user);
        if (result != -1) {
            message.setCode(RequestResultType.SUCCESS);
            message.setMessage(result);
        } else {
            message.setCode(RequestResultType.FAILED);
            message.setMessage("用户名已存在");
        }
        return message;
    }

    @RequestMapping(path = "/delete_user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteUsers(@RequestBody(required = true) int[] ids) {
        ResponseMessage responseMessage = new ResponseMessage();
        boolean result = userService.deleteUsers(ids);
        if (result) {
            responseMessage.setCode(RequestResultType.SUCCESS);
            responseMessage.setMessage("删除用户成功");
        } else {
            responseMessage.setCode(RequestResultType.FAILED);
            responseMessage.setMessage("删除用户失败");
        }

        return responseMessage;
    }

    @RequestMapping(path = "/query_user", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryUsers(@RequestBody BasePagination pagination) {
        Map<String, Object> response = new HashMap<>();
        List<User> list = userService.getUsers(pagination);
        response.put("list", list);
        response.put("pagination", pagination);
        return response;
    }

    @RequestMapping(path = "/update_user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateUser(@RequestBody @Validated(ValidateUpdate.class) User user,
            BindingResult bindingResult) {
        ResponseMessage responseMessage = new ResponseMessage();
        if (bindingResult.hasErrors()) {
            responseMessage.setCode(RequestResultType.FAILED);
            responseMessage.setMessage(ValidateUtils.transformErrorsToMap(bindingResult.getFieldErrors()));
            return responseMessage;
        }
        if (userService.updateUser(user)) {
            responseMessage.setCode(RequestResultType.SUCCESS);
            responseMessage.setMessage("更新用户信息成功");
        } else {
            responseMessage.setCode(RequestResultType.FAILED);
            responseMessage.setMessage("更新用户信息失败");
        }
        return responseMessage;
    }

    @RequestMapping(path = "/get_user", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(@RequestParam(name = "id", required = true) int id) {
        return userService.getUserById(id);
    }

    @RequestMapping(path = "/update_base_info", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateBaseInfo(@RequestBody @Validated(ValidatePerson.class) User user,
            BindingResult bindingResult) {
        user.setRoleType(getCurrentUserInfo().getRoleType());
        user.setPassword(null);
        user.setId(getCurrentUserInfo().getId());
        user.setUserName(getCurrentUserInfo().getUserName());
        ResponseMessage responseMessage = new ResponseMessage();
        if (bindingResult.hasErrors()) {
            responseMessage.setCode(RequestResultType.FAILED);
            responseMessage.setMessage(ValidateUtils.transformErrorsToMap(bindingResult.getFieldErrors()));
            return responseMessage;
        }
        if (userService.updateUser(user)) {
            setSessionAttribute(Constants.SESSION_USER, userService.getUserById(getCurrentUserInfo().getId()));
            responseMessage.setCode(RequestResultType.SUCCESS);
            responseMessage.setMessage("更新用户信息成功");
        } else {
            responseMessage.setCode(RequestResultType.FAILED);
            responseMessage.setMessage("更新用户信息失败");
        }
        return responseMessage;
    }

    @RequestMapping(path = "/update_password", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage UpdatePassword(@RequestParam(name = "oldPwd", required = true) String oldPwd,
            @RequestParam(name = "newPwd", required = true) String newPwd) {
        ResponseMessage message = new ResponseMessage();
        if (userService.login(getCurrentUserInfo().getUserName(), StringUtil.EncoderByMd5(oldPwd)) == null) {
            message.setCode(RequestResultType.FAILED);
            message.setMessage("旧密码不正确");
            return message;
        }
        User user = new User();
        user.setId(getCurrentUserInfo().getId());
        user.setPassword(newPwd);
        userService.updateUserPassword(user);
        message.setCode(RequestResultType.SUCCESS);
        message.setMessage("修改密码成功");
        return message;
    }
}
