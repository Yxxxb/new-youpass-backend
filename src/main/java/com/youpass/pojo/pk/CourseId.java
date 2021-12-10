package com.youpass.pojo.pk;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class CourseId implements Serializable {

    @SequenceGenerator(
            name = "Course_Sequence",
            sequenceName = "Course_Sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Course_Sequence"
    )
    @Column(name = "id")
    private Long id;


    @Embedded
    private TeacherId teacherId;
}
