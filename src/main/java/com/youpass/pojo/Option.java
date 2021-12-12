package com.youpass.pojo;

import com.youpass.pojo.pk.OptionId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "question_option")
public class Option implements Serializable {
    @EmbeddedId
    private OptionId id;

    @Column(length = 256,name = "content")
    private String content;

    @MapsId("questionId")
    @ManyToOne
    @JoinColumn(name="question_id",referencedColumnName = "question_id")
    private Question question;

    public Option() {
        id = new OptionId();
    }

    public static Option.Builder Builder(){
        return new Builder();
    }

    public static class Builder{
        private OptionId id;
        private String content;
        private Question question;

        Builder(){
            id = new OptionId();
        }

        public Builder setId(OptionId id) {
            this.id = id;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setQuestion(Question question) {
            this.question = question;
            return this;
        }

        public Option build() {
            var option = new Option();
            option.id = id;
            option.content = content;
            option.question = question;
            return option;
        }
    }

    public OptionId getId() {
        return id;
    }

    public void setId(OptionId id) {
        this.id = id;
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
