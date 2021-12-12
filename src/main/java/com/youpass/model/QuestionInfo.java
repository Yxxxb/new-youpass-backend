package com.youpass.model;

import java.util.Date;
import java.util.List;

public class QuestionInfo {
    private  Long questionId;
    private  Long courseId;

    public Long getExamId() {
        return examId;
    }

    @Override
    public String toString() {
        return "QuestionInfo{" +
                "questionId=" + questionId +
                ", courseId=" + courseId +
                ", examId=" + examId +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", standardAnswer='" + standardAnswer + '\'' +
                ", subject='" + subject + '\'' +
                ", createTime=" + createTime +
                ", optionInfoList=" + optionInfoList +
                '}';
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    private Long examId;
    private String description;
    private Integer type;
    private String standardAnswer;
    private String subject;
    private Date createTime;
    private List<OptionInfo> optionInfoList;

    public QuestionInfo(Long questionId, Long courseId, Long examId, String description, Integer type, String standardAnswer, String subject, Date createTime, List<OptionInfo> optionInfoList) {
        this.questionId = questionId;
        this.courseId = courseId;
        this.examId = examId;
        this.description = description;
        this.type = type;
        this.standardAnswer = standardAnswer;
        this.subject = subject;
        this.createTime = createTime;
        this.optionInfoList = optionInfoList;
    }

    public QuestionInfo() {
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStandardAnswer() {
        return standardAnswer;
    }

    public void setStandardAnswer(String standardAnswer) {
        this.standardAnswer = standardAnswer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public List<OptionInfo> getOptionInfoList() {
        return optionInfoList;
    }

    public void setOptionInfoList(List<OptionInfo> optionInfoList) {
        this.optionInfoList = optionInfoList;
    }

}
