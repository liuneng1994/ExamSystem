package com.adrian.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.adrian.command.BasePagination;
import com.adrian.command.ExamDetail;
import com.adrian.common.ResponseMessage;
import com.adrian.model.Exam;
import com.adrian.service.ExamService;
import com.adrian.type.RequestResultType;

@RestController
@RequestMapping("/request/exam")
public class ExamController {
    @Resource(name = "examService")
    private ExamService examService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseMessage add(@RequestBody ExamDetail examDetail) {
        ResponseMessage responseMessage = new ResponseMessage();
        examService.createExam(examDetail);
        responseMessage.setCode(RequestResultType.SUCCESS);
        responseMessage.setMessage(examDetail.getId());
        return responseMessage;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseMessage getById(@RequestParam(name = "id", required = true) int id) {
        ResponseMessage responseMessage = new ResponseMessage();
        ExamDetail examDetail = examService.getExamDetailById(id);
        responseMessage.setCode(RequestResultType.SUCCESS);
        responseMessage.setMessage(examDetail);
        return responseMessage;
    }

    @RequestMapping(path = "/getExams", method = RequestMethod.POST)
    public ResponseMessage getExams(BasePagination pagination) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<Exam> list = examService.getExams(pagination);
        responseMessage.setCode(RequestResultType.SUCCESS);
        responseMessage.setMessage(list);
        return responseMessage;
    }
}
