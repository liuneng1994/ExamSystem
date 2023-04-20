package com.adrian.dao;

import java.util.List;

import com.adrian.command.BasePagination;
import com.adrian.model.Question;

public interface QuestionDao {
    Question getQuestionById(int id);

    int addQuestion(Question question);

    void updateQuestion(Question question);

    void deleteQuestions(int[] ids);

    int getQuestionCountByKeyword(String keyword);

    List<Question> getQuestions(BasePagination pagination);
}
