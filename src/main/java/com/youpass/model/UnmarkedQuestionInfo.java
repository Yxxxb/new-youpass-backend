package com.youpass.model;

public class UnmarkedQuestionInfo {
    private Long questionId;
    private String description;
    private Integer restNumber;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRestNumber() {
        return restNumber;
    }

    public void setRestNumber(Integer restNumber) {
        this.restNumber = restNumber;
    }

    public UnmarkedQuestionInfo(Long questionId, String description, Integer restNumber) {
        this.questionId = questionId;
        this.description = description;
        this.restNumber = restNumber;
    }

    public UnmarkedQuestionInfo() {
    }
}
