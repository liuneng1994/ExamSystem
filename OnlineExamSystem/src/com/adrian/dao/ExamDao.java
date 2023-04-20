package com.adrian.dao;

import java.util.List;

import com.adrian.command.BasePagination;
import com.adrian.model.Exam;

public interface ExamDao {
    Exam getById(int id);

    List<Exam> getExams(BasePagination pagination);

    int add(Exam exam);

    int getExamCountByKeyword(String keyword);
}
