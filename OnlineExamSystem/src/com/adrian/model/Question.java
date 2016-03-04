package com.adrian.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.*;

import com.adrian.validate.group.ValidateUpdate;

public class Question {
    @NotNull(message = "����Id����Ϊ��", groups = { ValidateUpdate.class })
    private Integer id;

    @NotBlank(message = "���ⲻ��Ϊ��", groups = { ValidateUpdate.class })
    @Length(max = 100, message = "������󳤶Ȳ��ܳ���{max}", groups = { ValidateUpdate.class })
    private String question;

    @NotBlank(message = "ѡ��A����Ϊ��", groups = { ValidateUpdate.class })
    @Length(max = 45, message = "ѡ��A��󳤶Ȳ��ܳ���{max}", groups = { ValidateUpdate.class })
    private String choiceA;

    @NotBlank(message = "ѡ��A����Ϊ��", groups = { ValidateUpdate.class })
    @Length(max = 45, message = "ѡ��A��󳤶Ȳ��ܳ���{max}", groups = { ValidateUpdate.class })
    private String choiceB;

    @NotBlank(message = "ѡ��C����Ϊ��", groups = { ValidateUpdate.class })
    @Length(max = 45, message = "ѡ��C��󳤶Ȳ��ܳ���{max}", groups = { ValidateUpdate.class })
    private String choiceC;

    @NotBlank(message = "ѡ��D����Ϊ��", groups = { ValidateUpdate.class })
    @Length(max = 45, message = "ѡ��D��󳤶Ȳ��ܳ���{max}", groups = { ValidateUpdate.class })
    private String choiceD;

    // 1 A, 2 B, 3 C, 4 D
    @NotNull(message = "�𰸲���Ϊ��", groups = { ValidateUpdate.class })
    @Range(min = 1, max = 4, message = "��ѡ���ȷ", groups = { ValidateUpdate.class })
    private Integer answer;

    @Length(max = 100, message = "������Ϣ��󳤶Ȳ��ܳ���{max}", groups = { ValidateUpdate.class })
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
