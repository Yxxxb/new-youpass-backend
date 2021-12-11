package com.youpass.pojo.pk;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExamId implements Serializable {
//    @SequenceGenerator(
//            name = "Exam_Sequence",
//            sequenceName = "Exam_Sequence",
//            initialValue = 1,
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "Exam_Sequence"
//    )
    @Column(name = "exam_id")
    private Long examId;

    @Embedded
    private CourseId courseId;

    public ExamId() {
        courseId=new CourseId();
    }

    public ExamId(Long examId, Long courseId) {
        this.examId = examId;
        this.courseId = new CourseId(courseId);
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getCourseId() {
        return courseId.getCourseId();
    }

    public void setCourseId(Long courseId) {
        this.courseId = new CourseId(courseId);
    }
    public static ExamId.Builder Builder(){
        return new Builder();
    }
    public static class Builder{
        private Long examId;
        private CourseId courseId;
        public Builder setExamId(Long examId){
            this.examId=examId;
            return this;
        }
        public Builder setCourseId(Long courseId){
            this.courseId = new CourseId(courseId);
            return this;
        }
        public ExamId build(){
            ExamId examId=new ExamId();
            examId.examId=this.examId;
            examId.courseId =this.courseId;
            return examId;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamId examId1 = (ExamId) o;
        return Objects.equals(examId, examId1.examId) && Objects.equals(courseId, examId1.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examId, courseId);
    }
}
