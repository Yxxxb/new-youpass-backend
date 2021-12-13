package com.youpass.model.ReturnType;

import java.util.Date;
import java.util.List;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class ExamQuestionReturn {
    private String title;
    private Date startTime;
    private Date endTime;

    private List<QuestionInfoReturn> questionList;

    public ExamQuestionReturn() {
    }

    public ExamQuestionReturn(String title, Date startTime, Date endTime, List<QuestionInfoReturn> questionList) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questionList = questionList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<QuestionInfoReturn> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionInfoReturn> questionList) {
        this.questionList = questionList;
    }
}
