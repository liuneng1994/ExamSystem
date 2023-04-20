package com.adrian.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adrian.command.BasePagination;
import com.adrian.command.ExamDetail;
import com.adrian.dao.ExamDao;
import com.adrian.dao.UserExamRecordDao;
import com.adrian.model.Exam;
import com.adrian.service.ExamService;
import com.adrian.util.AssertUtil;
import com.adrian.util.StringUtil;

@Service("examService")
public class ExamServiceImpl implements ExamService {
    @Resource(name = "examDao")
    private ExamDao examDao;

    @Resource(name = "userExamRecordDao")
    private UserExamRecordDao userExamRecordDao;

    @Override
    public void createExam(ExamDetail examDetail) {
        AssertUtil.assertNotNull(examDetail, "Exam detail cannot be null");
        AssertUtil.assertNotNull(examDetail.getStudentList(), "Student list cannot be null");
        examDao.add(examDetail);
        userExamRecordDao.addRecords(examDetail.getStudentList());
    }

    @Override
    public ExamDetail getExamDetailById(int id) {
        if (id <= 0) {
            return new ExamDetail();
        }
        ExamDetail result = new ExamDetail(examDao.getById(id));
        if (result.getId() == null) {
            return new ExamDetail();
        }

        result.setStudentList(userExamRecordDao.getStudentsByExamId(result.getId()));
        return result;
    }

    @Override
    public List<Exam> getExams(BasePagination pagination) {
        if (pagination.getKeyword() == null) {
            return new ArrayList<>();
        }
        String keyword = StringUtil.stringIsNullOrEmpty(pagination.getKeyword()) ? StringUtil.STRING_EMPTY
                : pagination.getKeyword();
        int count = examDao.getExamCountByKeyword(keyword);
        pagination.setTotalCount(count);
        List<Exam> list = new ArrayList<>();
        if (pagination.checked()) {
            list = examDao.getExams(pagination);
        }
        return list;
    }
}
