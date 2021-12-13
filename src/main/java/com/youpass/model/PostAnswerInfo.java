package com.youpass.model;

import java.util.List;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class PostAnswerInfo {
    private Long questionId;
    private String stuFillAnswer;
    private List<Character> stuChoiceAnswer;


    public PostAnswerInfo() {
    }

    public PostAnswerInfo(Long questionId, String stuFillAnswer, List<Character> stuChoiceAnswer) {
        this.questionId = questionId;
        this.stuFillAnswer = stuFillAnswer;
        this.stuChoiceAnswer = stuChoiceAnswer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getStuFillAnswer() {
        return stuFillAnswer;
    }

    public void setStuFillAnswer(String stuFillAnswer) {
        this.stuFillAnswer = stuFillAnswer;
    }

    public List<Character> getStuChoiceAnswer() {
        return stuChoiceAnswer;
    }

    public void setStuChoiceAnswer(List<Character> stuChoiceAnswer) {
        this.stuChoiceAnswer = stuChoiceAnswer;
    }
}
