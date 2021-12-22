package com.youpass.model.ReturnType;

import com.youpass.model.OptionInfo;

import java.util.List;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class QuestionInfoReturn {
    private Long questionId;
    private String description;
    private Integer type;
    private Integer numInPaper;
    private List<OptionInfo> options;

    private boolean done = false;
    private String fill_content;
    private List<Integer> multiList;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getFill_content() {
        return fill_content;
    }

    public void setFill_content(String fill_content) {
        this.fill_content = fill_content;
    }

    public List<Integer> getMultiList() {
        return multiList;
    }

    public void setMultiList(List<Integer> multiList) {
        this.multiList = multiList;
    }

    public QuestionInfoReturn() {
    }

    public QuestionInfoReturn(Long questionId, String description, Integer type, Integer numInPaper, List<OptionInfo> options, boolean done, String fill_cotent, List<Integer> multiList) {
        this.questionId = questionId;
        this.description = description;
        this.type = type;
        this.numInPaper = numInPaper;
        this.options = options;
        this.done = done;
        this.fill_content = fill_cotent;
        this.multiList = multiList;
    }

    public QuestionInfoReturn(Long questionId, String description, Integer type, Integer numInPaper, List<OptionInfo> options) {
        this.questionId = questionId;
        this.description = description;
        this.type = type;
        this.numInPaper = numInPaper;
        this.options = options;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNumInPaper() {
        return numInPaper;
    }

    public void setNumInPaper(Integer numInPaper) {
        this.numInPaper = numInPaper;
    }

    public List<OptionInfo> getOptions() {
        return options;
    }

    public void setOptions(List<OptionInfo> options) {
        this.options = options;
    }
}
