package com.youpass.pojo.pk;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ExamId implements Serializable {
    @SequenceGenerator(
            name = "Exam_Sequence",
            sequenceName = "Exam_Sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Exam_Sequence"
    )
    @Column(name = "exam_id")
    private Long examId;

    @Embedded
    private CourseId courseId;

    public ExamId() {
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
}
