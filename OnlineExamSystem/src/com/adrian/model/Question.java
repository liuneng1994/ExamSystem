package com.adrian.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.*;

import com.adrian.validate.group.ValidateUpdate;

public class Question {
    @NotNull(message = "问题Id不能为空", groups = { ValidateUpdate.class })
    private Integer id;

    @NotBlank(message = "问题不能为空", groups = { ValidateUpdate.class })
    @Length(max = 100, message = "问题最大长度不能超过{max}", groups = { ValidateUpdate.class })
    private String question;

    @NotBlank(message = "选项A不能为空", groups = { ValidateUpdate.class })
    @Length(max = 45, message = "选项A最大长度不能超过{max}", groups = { ValidateUpdate.class })
    private String choiceA;

    @NotBlank(message = "选项A不能为空", groups = { ValidateUpdate.class })
    @Length(max = 45, message = "选项A最大长度不能超过{max}", groups = { ValidateUpdate.class })
    private String choiceB;

    @NotBlank(message = "选项C不能为空", groups = { ValidateUpdate.class })
    @Length(max = 45, message = "选项C最大长度不能超过{max}", groups = { ValidateUpdate.class })
    private String choiceC;

    @NotBlank(message = "选项D不能为空", groups = { ValidateUpdate.class })
    @Length(max = 45, message = "选项D最大长度不能超过{max}", groups = { ValidateUpdate.class })
    private String choiceD;

    // 1 A, 2 B, 3 C, 4 D
    @NotNull(message = "答案不能为空", groups = { ValidateUpdate.class })
    @Range(min = 1, max = 4, message = "答案选项不正确", groups = { ValidateUpdate.class })
    private Integer answer;

    @Length(max = 100, message = "描述信息最大长度不能超过{max}", groups = { ValidateUpdate.class })
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
