package com.adrian.dao;

import java.util.List;

import com.adrian.model.UserExamRecord;

public interface UserExamRecordDao {
    List<UserExamRecord> getRecordsByExamId(int id);

    List<UserExamRecord> getRecordsByUserId(int id);

    List<UserExamRecord> getStudentsByExamId(int id);

    void addRecords(List<UserExamRecord> list);
}
