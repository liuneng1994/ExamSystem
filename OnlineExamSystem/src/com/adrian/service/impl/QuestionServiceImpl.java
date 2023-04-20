package com.adrian.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adrian.command.BasePagination;
import com.adrian.dao.QuestionDao;
import com.adrian.model.Question;
import com.adrian.service.QuestionService;
import com.adrian.util.AssertUtil;
import com.adrian.util.StringUtil;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Resource(name = "questionDao")
    private QuestionDao questionDao;

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public Question getQuestionById(int id) {
        AssertUtil.assertMinValue(id, 1, "Id cannot be minimum then 1");
        return questionDao.getQuestionById(id);
    }

    @Override
    public int addQuestion(Question question) {
        AssertUtil.assertNotNull(question, "Question cannot be null");
        return questionDao.addQuestion(question);
    }

    @Override
    public void updateQuestion(Question question) {
        AssertUtil.assertNotNull(question, "Question cannot be null");
        questionDao.updateQuestion(question);
    }

    @Override
    public void deleteQuestions(int[] ids) {
        AssertUtil.assertNotNull(ids, "Ids cannot be null");
        questionDao.deleteQuestions(ids);
    }

    @Override
    public int getQuestionCountByKeyword(String keyword) {
        AssertUtil.assertNotNull(keyword, "Keyword cannot be null");
        return questionDao.getQuestionCountByKeyword(keyword);
    }

    @Override
    public List<Question> getQuestions(BasePagination pagination) {
        if (pagination.getKeyword() == null) {
            return new ArrayList<>();
        }
        String keyword = StringUtil.stringIsNullOrEmpty(pagination.getKeyword()) ? StringUtil.STRING_EMPTY
                : pagination.getKeyword();
        int count = questionDao.getQuestionCountByKeyword(keyword);
        pagination.setTotalCount(count);
        List<Question> list = new ArrayList<>();
        if (pagination.checked()) {
            list = questionDao.getQuestions(pagination);
        }
        return list;
    }
}
