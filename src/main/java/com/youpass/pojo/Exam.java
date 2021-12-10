package com.youpass.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Exam implements Serializable {
    @Id
    @Column(name = "course_id", insertable = false, updatable = false)
    private Long course_id;

    @Id
    @Column(name = "exam_id")
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
    private Long id;

    @Column(name = "start_time")
    private Date start_time;
    @Column(name = "end_time")
    private Date end_time;
    @Column(name = "nature")
    private Integer nature;
    @Column(length = 64, name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_exam",
            //joinColumns,当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name = "exam_id"), @JoinColumn(name = "course_id")},
            //inverseJoinColumns，对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<Student> students;

    public Exam() {
    }
}
