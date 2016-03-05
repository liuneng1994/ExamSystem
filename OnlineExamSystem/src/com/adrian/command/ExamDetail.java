package com.adrian.command;

import java.util.List;

import com.adrian.model.Exam;
import com.adrian.model.UserExamRecord;

public class ExamDetail extends Exam {
    private List<UserExamRecord> studentList;

    public List<UserExamRecord> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<UserExamRecord> studentList) {
        this.studentList = studentList;
    }
}
