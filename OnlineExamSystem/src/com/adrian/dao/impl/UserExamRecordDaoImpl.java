package com.adrian.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.adrian.dao.UserExamRecordDao;
import com.adrian.model.UserExamRecord;

@Repository("userExamRecordDao")
public class UserExamRecordDaoImpl extends SqlSessionDaoSupport implements UserExamRecordDao {
    private static final String CLASS_NAME = UserExamRecord.class.getName();
    private static final String GET_RECORDS_BY_EXAM_ID = ".getRecordsByExamId";
    private static final String GET_RECORDS_BY_USER_ID = ".getRecordsByUserId";
    private static final String ADD_RECORDS = ".addRecords";
    private static final String GET_STUDENTS = "getStudentsByExamId";

    @Override
    public List<UserExamRecord> getRecordsByExamId(int id) {
        return getSqlSession().selectList(CLASS_NAME + GET_RECORDS_BY_EXAM_ID, id);
    }

    @Override
    public List<UserExamRecord> getRecordsByUserId(int id) {
        return getSqlSession().selectList(CLASS_NAME + GET_RECORDS_BY_USER_ID, id);
    }

    @Override
    public void addRecords(List<UserExamRecord> list) {
        getSqlSession().insert(CLASS_NAME + ADD_RECORDS, list);
    }

    @Override
    public List<UserExamRecord> getStudentsByExamId(int id) {
        return getSqlSession().selectList(CLASS_NAME + GET_STUDENTS, id);
    }
}
