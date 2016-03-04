package com.adrian.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.adrian.command.BasePagination;
import com.adrian.common.ResponseMessage;
import com.adrian.model.Question;
import com.adrian.service.QuestionService;
import com.adrian.type.RequestResultType;
import com.adrian.validate.group.ValidateUpdate;
import com.adrian.validate.util.ValidateUtils;

@RestController
@RequestMapping("/request/question")
public class QuestionController extends BaseController {
    @Resource(name = "questionService")
    private QuestionService questionService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseMessage add(@RequestBody @Valid Question question, BindingResult bindingResult) {
        ResponseMessage responseMessage = new ResponseMessage();
        if (bindingResult.hasErrors()) {
            responseMessage.setCode(RequestResultType.FAILED);
            responseMessage.setMessage(ValidateUtils.transformErrorsToMap(bindingResult.getFieldErrors()));
            return responseMessage;
        }
        int id = questionService.addQuestion(question);
        responseMessage.setCode(RequestResultType.SUCCESS);
        responseMessage.setMessage(id);
        return responseMessage;
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public ResponseMessage update(@RequestBody @Validated(ValidateUpdate.class) Question question,
            BindingResult bindingResult) {
        ResponseMessage responseMessage = new ResponseMessage();
        if (bindingResult.hasErrors()) {
            responseMessage.setCode(RequestResultType.FAILED);
            responseMessage.setMessage(ValidateUtils.transformErrorsToMap(bindingResult.getFieldErrors()));
            return responseMessage;
        }
        questionService.updateQuestion(question);
        responseMessage.setCode(RequestResultType.SUCCESS);
        responseMessage.setMessage("试题修改成功");
        return responseMessage;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ResponseMessage delete(@RequestBody(required = true) int[] ids) {
        ResponseMessage responseMessage = new ResponseMessage();
        if (ids.length == 0) {
            responseMessage.setCode(RequestResultType.FAILED);
            responseMessage.setMessage("请选择需要删除的试题");
        }
        questionService.deleteQuestions(ids);
        responseMessage.setCode(RequestResultType.SUCCESS);
        responseMessage.setMessage("试题删除成功");
        return responseMessage;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public Question getById(@RequestParam(name = "id", required = true) int id) {
        Question question = questionService.getQuestionById(id);
        question = question == null ? new Question() : question;
        return question;
    }

    @RequestMapping(path = "/get_questions")
    public Map<String, Object> getQuestions(@RequestBody BasePagination pagination) {
        Map<String, Object> response = new HashMap<>();
        List<Question> list = questionService.getQuestions(pagination);
        response.put("list", list);
        response.put("pagination", pagination);
        return response;
    }
}
