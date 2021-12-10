package com.youpass.pojo.pk;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
public class ExamInfoId implements Serializable {
    @Embedded
    private StudentId studentId;
    @Embedded
    private ExamId examId;

    public ExamInfoId(Long studentId, Long examId,Long courseId) {
        this.studentId = new StudentId(studentId);
        this.examId = new ExamId(examId,courseId);
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public void setStudentId(StudentId studentId) {
        this.studentId = studentId;
    }

    public ExamId getExamId() {
        return examId;
    }

    public void setExamId(ExamId examId) {
        this.examId = examId;
    }

    public ExamInfoId() {
    }

    public static ExamInfoId.Builder Builder(){
        return new Builder();
    }
    public static class Builder{
        private StudentId studentId;
        private ExamId examId;
        public Builder setStudentId(Long studentId){
            this.studentId.setStudentId(studentId);
            return this;
        }
        public Builder setExamId(Long examId,Long courseId){
            this.examId.setExamId(examId);
            this.examId.setCourseId(courseId);
            return this;
        }
        public ExamInfoId build(){
            ExamInfoId examInfoId=new ExamInfoId();
            examInfoId.examId=this.examId;
            examInfoId.studentId=this.studentId;
            return examInfoId;
        }
    }
}
