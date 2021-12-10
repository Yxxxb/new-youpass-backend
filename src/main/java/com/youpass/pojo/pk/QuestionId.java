package com.youpass.pojo.pk;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class QuestionId implements Serializable {
    @SequenceGenerator(
            name = "Question_Sequence",
            sequenceName = "Question_Sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Question_Sequence"
    )
    @Column(name = "question_id")
    private Long questionId;

    public QuestionId() {
    }

    public QuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public static QuestionId.Builder Builder() {
        return new Builder();
    }

    public static class Builder {
        private Long questionId;

        public Builder setQuestionId(Long questionId) {
            this.questionId = questionId;
            return this;
        }

        public QuestionId build() {
            QuestionId questionId = new QuestionId();
            questionId.questionId = this.questionId;
            return questionId;
        }
    }
}
