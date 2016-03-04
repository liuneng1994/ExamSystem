package com.adrian.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adrian.command.BasePagination;
import com.adrian.dao.UserDao;
import com.adrian.model.User;
import com.adrian.service.UserService;
import com.adrian.util.AssertUtil;
import com.adrian.util.StringUtil;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String userName, String password) {
        boolean status = false;
        if (!StringUtil.stringIsNullOrWhiteSpace(userName) && !StringUtil.stringIsNullOrWhiteSpace(password)) {
            status = true;
        }

        User user = null;
        if (status) {
            user = this.userDao.getUserByName(userName);
            if (user != null) {
                status = password.equals(user.getPassword());
            }
        }

        user = status ? user : null;
        if (user != null) {
            user.setPassword(StringUtil.STRING_EMPTY);
        }

        return user;
    }

    @Override
    public int addUser(User user) {
        AssertUtil.assertNotNull(user, "user cannot be null");
        int result = -1;
        boolean status = false;
        status = userDao.getUserByName(user.getUserName()) == null ? true : false;

        if (status) {
            user.setPassword(StringUtil.EncoderByMd5("123456"));
            userDao.addUser(user);
            result = user.getId();
        }
        return result;
    }

    @Override
    public boolean deleteUsers(int[] ids) {
        AssertUtil.assertNotNull(ids, "Array of id cannot be empty or null");
        if (ids.length > 0) {
            userDao.deleteUsers(ids);
        }
        return true;
    }

    @Override
    public List<User> getUsers(BasePagination pagination) {
        if (pagination.getKeyword() == null) {
            return new ArrayList<>();
        }
        int count = userDao.getUserCountByPagination(pagination.getKeyword());
        pagination.setTotalCount(count);
        List<User> list = new ArrayList<>();
        if (pagination.checked()) {
            list = userDao.getUsers(pagination);
        }
        return list;
    }

    @Override
    public boolean updateUser(User user) {
        AssertUtil.assertNotNull(user, "User cannot be null");
        if (user.getId() == null && user.getUserName() == null) {
            throw new IllegalStateException("User's id and user name are null");
        }

        userDao.updateUserBaseInfo(user);
        if (user.getPassword() != null) {
            user.setPassword(StringUtil.EncoderByMd5(user.getPassword()));
            userDao.updateUserPassword(user);
        }

        return true;
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public void updateUserPassword(User user) {
        AssertUtil.assertNotNull(user.getId(), "Id cannot be null");
        AssertUtil.assertStringNotEmpty(user.getPassword(), "Password cannot be empty");
        user.setPassword(StringUtil.EncoderByMd5(user.getPassword()));
        userDao.updateUserPassword(user);
    }
}
