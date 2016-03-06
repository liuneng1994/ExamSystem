package com.adrian.service;

import java.util.List;

import com.adrian.command.BasePagination;
import com.adrian.command.ExamDetail;
import com.adrian.model.Exam;

public interface ExamService {
    void createExam(ExamDetail examDetail);

    ExamDetail getExamDetailById(int id);

    List<Exam> getExams(BasePagination pagination);
}
