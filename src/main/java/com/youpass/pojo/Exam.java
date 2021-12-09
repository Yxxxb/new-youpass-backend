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

    public Exam(Long course_id, Long id, Date start_time, Date end_time, Integer nature, String title, Course course) {
        this.course_id = course_id;
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.nature = nature;
        this.title = title;
        this.course = course;

    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
