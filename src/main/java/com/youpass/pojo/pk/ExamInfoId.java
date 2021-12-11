package com.youpass.pojo.pk;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExamInfoId implements Serializable {
    @Embedded
    private StudentId studentId;
    @Embedded
    private ExamId examId;

    public ExamInfoId() {
    }

    public ExamInfoId(Long studentId, Long examId,Long courseId) {
        this.studentId = new StudentId(studentId);
        this.examId = new ExamId(examId,courseId);
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

    public Long getCourseId(){
        return examId.getCourseId();
    }

    public void setExamId(Long examId,Long courseId) {
        this.examId = new ExamId(examId,courseId);
    }

    public static ExamInfoId.Builder Builder(){
        return new Builder();
    }
    public static class Builder{
        private StudentId studentId;
        private ExamId examId;
        public Builder setStudentId(Long studentId){
            this.studentId = new StudentId(studentId);
            return this;
        }
        public Builder setExamId(Long examId,Long courseId){
            this.examId = new ExamId(examId,courseId);
            return this;
        }
        public ExamInfoId build(){
            ExamInfoId examInfoId=new ExamInfoId();
            examInfoId.examId=this.examId;
            examInfoId.studentId=this.studentId;
            return examInfoId;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamInfoId that = (ExamInfoId) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(examId, that.examId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, examId);
    }
}
