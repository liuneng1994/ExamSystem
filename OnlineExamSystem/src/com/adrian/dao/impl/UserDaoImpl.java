package com.adrian.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.command.BasePagination;
import com.adrian.dao.UserDao;
import com.adrian.model.User;

@Repository("userDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
    private static final String CLASS_NAME = UserDao.class.getName();
    private static final String GET_USER_BY_NAME = ".getUserByName";
    private static final String GET_USER_BY_ID = ".getUserById";
    private static final String ADD_USER = ".addUser";
    private static final String DELETE_USERS = ".deleteUsers";
    private static final String GET_USER_COUNT = ".getUserCountByPagination";
    private static final String GET_USERS = ".getUsers";
    private static final String UPDATE_USER_BASE_INFO = ".updateUserBaseInfo";
    private static final String UPDATE_USER_PASSWORD = ".updateUserPassword";

    @Override
    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    };

    @Override
    public User getUserByName(String userName) {
        return getSqlSession().selectOne(CLASS_NAME + GET_USER_BY_NAME, userName);
    }

    @Override
    public int addUser(User user) {
        int id = getSqlSession().insert(CLASS_NAME + ADD_USER, user);
        return id;
    }

    @Override
    public void deleteUsers(int[] ids) {
        getSqlSession().delete(CLASS_NAME + DELETE_USERS, ids);
    }

    @Override
    public int getUserCountByPagination(String keyword) {
        return getSqlSession().selectOne(CLASS_NAME + GET_USER_COUNT, keyword);
    }

    @Override
    public List<User> getUsers(BasePagination pagination) {
        List<User> list = getSqlSession().selectList(CLASS_NAME + GET_USERS, pagination);
        return list;
    }

    @Override
    public void updateUserBaseInfo(User user) {
        getSqlSession().update(CLASS_NAME + UPDATE_USER_BASE_INFO, user);
    }

    @Override
    public void updateUserPassword(User user) {
        getSqlSession().update(CLASS_NAME + UPDATE_USER_PASSWORD, user);
    }

    @Override
    public User getUserById(int id) {
        return getSqlSession().selectOne(CLASS_NAME + GET_USER_BY_ID, id);
    }

}
