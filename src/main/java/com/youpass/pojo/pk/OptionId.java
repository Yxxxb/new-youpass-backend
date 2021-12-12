package com.youpass.pojo.pk;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OptionId implements Serializable {
    @Column(name="option_id")
//    @SequenceGenerator(
//            name = "Option_Sequence",
//            sequenceName = "Option_Sequence",
//            initialValue = 1,
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "Option_Sequence"
//    )
    private Long optionId;

    @Embedded
    private QuestionId questionId;

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public QuestionId getQuestionId() {
        return questionId;
    }

    public void setQuestionId(QuestionId questionId) {
        this.questionId = questionId;
    }

    public OptionId() {
    }

    public OptionId(Long optionId) {
        this.optionId = optionId;
    }

    public OptionId(Long optionId, Long questionId) {
        this.optionId = optionId;
        this.questionId = new QuestionId(questionId);
    }

    public OptionId(Long optionId, QuestionId questionId) {
        this.optionId = optionId;
        this.questionId = questionId;
    }

    //    public static OptionId.Builder Builder(){
//        return new Builder();
//    }
//    public static class Builder{
//        private Long optionId;
//        private QuestionId questionId;
//        public Builder setOptionId(Long optionId){
//            this.optionId=optionId;
//            return this;
//        }
//        public Builder setQuestionId(Long questionId){
//            this.questionId= new QuestionId(questionId);
//            return this;
//        }
//        public OptionId build(){
//            OptionId optionId=new OptionId();
//            optionId.optionId=this.optionId;
//            optionId.questionId=this.questionId;
//            return optionId;
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionId optionId1 = (OptionId) o;
        return Objects.equals(optionId, optionId1.optionId) && Objects.equals(questionId, optionId1.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId, questionId);
    }
}
