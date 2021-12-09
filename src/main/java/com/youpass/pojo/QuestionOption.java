package com.youpass.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Question_Option")
@Table
public class QuestionOption implements Serializable {
    @Id
    @Column(name="question_id", insertable=false,updatable=false)
    private Long question_id;

    @Id
    @Column(name="option_id")
    @SequenceGenerator(
            name = "Option_Sequence",
            sequenceName = "Option_Sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Option_Sequence"
    )
    private Long option_id;

    @Column(length = 256,name = "content")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="question_id", insertable=false, updatable=false)
    private Question question;

    public QuestionOption(Long question_id, Long option_id, String content, Question question) {
        this.question_id = question_id;
        this.option_id = option_id;
        this.content = content;
        this.question = question;
    }

    public QuestionOption() {
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public Long getOption_id() {
        return option_id;
    }

    public void setOption_id(Long option_id) {
        this.option_id = option_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
