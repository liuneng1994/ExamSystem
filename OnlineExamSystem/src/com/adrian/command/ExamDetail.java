package com.adrian.command;

import java.util.List;

import com.adrian.model.Exam;
import com.adrian.model.UserExamRecord;

public class ExamDetail extends Exam {
    private List<UserExamRecord> studentList;

    public ExamDetail() {
        // nothing to do
    }

    public ExamDetail(Exam exam) {
        setDescription(exam.getDescription());
        setDuration(exam.getDuration());
        setEffectiveTime(exam.getEffectiveTime());
        setEndTime(exam.getEndTime());
        setExamName(exam.getExamName());
        setId(exam.getId());
        setPassCriteria(exam.getPassCriteria());
        setQuestionCount(exam.getQuestionCount());
        setSingleScore(exam.getSingleScore());
    }

    public List<UserExamRecord> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<UserExamRecord> studentList) {
        this.studentList = studentList;
    }
}
