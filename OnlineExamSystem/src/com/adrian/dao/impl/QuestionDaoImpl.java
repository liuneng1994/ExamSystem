package com.adrian.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.command.BasePagination;
import com.adrian.dao.QuestionDao;
import com.adrian.model.Question;

@Repository("questionDao")
public class QuestionDaoImpl extends SqlSessionDaoSupport implements QuestionDao {
    private static final String CLASS_NAME = QuestionDao.class.getName();
    private static final String GET_QUESTION_BY_ID = ".getQuestionById";
    private static final String ADD_QUESTION = ".addQuestion";
    private static final String UPDATE_QUESTION = ".updateQuestion";
    private static final String DELETE_QUESTIONS = ".deleteQuestions";
    private static final String GET_QUESTION_COUNT_BT_KEYWORD = ".getQuestionCountByKeyword";
    private static final String GET_QUESTIONS = ".getQuestions";

    @Override
    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    };

    @Override
    public Question getQuestionById(int id) {
        return getSqlSession().selectOne(CLASS_NAME + GET_QUESTION_BY_ID, id);
    }

    @Override
    public int addQuestion(Question question) {
        return getSqlSession().insert(CLASS_NAME + ADD_QUESTION, question);
    }

    @Override
    public void updateQuestion(Question question) {
        getSqlSession().update(CLASS_NAME + UPDATE_QUESTION, question);
    }

    @Override
    public void deleteQuestions(int[] ids) {
        getSqlSession().delete(CLASS_NAME + DELETE_QUESTIONS, ids);
    }

    @Override
    public int getQuestionCountByKeyword(String keyword) {
        return getSqlSession().selectOne(CLASS_NAME + GET_QUESTION_COUNT_BT_KEYWORD, keyword);
    }

    @Override
    public List<Question> getQuestions(BasePagination pagination) {
        return getSqlSession().selectList(CLASS_NAME + GET_QUESTIONS, pagination);
    }

}
