package com.adrian.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.adrian.command.BasePagination;
import com.adrian.dao.ExamDao;
import com.adrian.model.Exam;

@Repository("examDao")
public class ExamDaoImpl extends SqlSessionDaoSupport implements ExamDao {
    private static final String CLASS_NAME = ExamDao.class.getName();
    private static final String GET_BY_ID = ".getById";
    private static final String ADD = ".add";
    private static final String GET_EXAMS = ".getExams";

    @Override
    public Exam getById(int id) {
        return getSqlSession().selectOne(CLASS_NAME + GET_BY_ID, id);
    }

    @Override
    public int add(Exam exam) {
        return getSqlSession().insert(CLASS_NAME + ADD, exam);
    }

    @Override
    public List<Exam> getExams(BasePagination pagination) {
        return getSqlSession().selectList(CLASS_NAME + GET_EXAMS, pagination);
    }

}
