package com.youpass.pojo.pk;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
public class ExaminationPaperId implements Serializable {
    @Embedded
    private StudentId studentId;

    @Embedded
    private ExamId examId;

    @Embedded
    private QuestionId questionId;

    public ExaminationPaperId() {
    }

    public ExaminationPaperId(Long studentId, Long examId,Long courseId, Long questionId) {
        this.studentId = new StudentId(studentId);
        this.examId = new ExamId(examId,courseId);
        this.questionId = new QuestionId(questionId);
    }

    public Long getStudentId() {
        return studentId.getStudentId();
    }

    public void setStudentId(Long studentId) {
        this.studentId = new StudentId(studentId);
    }

    public Long getExamId() {
        return examId.getExamId();
    }

    public Long getCourseId() {
        return examId.getCourseId();
    }

    public void setExamId(Long examId,Long courseId) {
        this.examId = new ExamId(examId,courseId);
    }

    public Long getQuestionId() {
        return questionId.getQuestionId();
    }

    public void setQuestionId(Long questionId) {
        this.questionId = new QuestionId(questionId);
    }
    public static ExaminationPaperId.Builder Builder(){
        return new Builder();
    }
    public static class Builder{

        private StudentId studentId;
        private ExamId examId;
        private QuestionId questionId;

        public Builder setStudentId(Long studentId){
            this.studentId.setStudentId(studentId);
            return this;
        }
        public Builder setExamId(Long examId,Long courseId){
            this.examId.setExamId(examId);
            this.examId.setCourseId(courseId);
            return this;
        }
        public Builder setQuestionId(Long questionId){
            this.questionId.setQuestionId(questionId);
            return this;
        }
        public ExaminationPaperId build(){
            ExaminationPaperId examinationPaperId=new ExaminationPaperId();
            examinationPaperId.examId=this.examId;
            examinationPaperId.studentId=this.studentId;
            examinationPaperId.questionId=this.questionId;
            return examinationPaperId;
        }
    }
}
