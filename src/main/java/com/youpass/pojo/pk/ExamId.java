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

}
