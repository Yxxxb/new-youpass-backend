package com.youpass.pojo.pk;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class StudentId implements Serializable {
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", initialValue = 1950000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @Column(name = "student_id")
    private Long studentId;

}
