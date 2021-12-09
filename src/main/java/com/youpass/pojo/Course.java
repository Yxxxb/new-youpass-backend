package com.youpass.pojo;

import com.sun.xml.txw2.annotation.XmlCDATA;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Course {
    @Id
    @Column(name="course_id")
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
    private Long course_id;
    @Column(length = 64,name = "title")
    private String title;
    @Column(length = 32 ,name = "password")
    private String password;

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course() {
    }

    public Course(String title, String password, Teacher teacher) {
        this.title = title;
        this.password = password;
        this.teacher = teacher;
    }

    public Course(Long course_id, String title, String password, Teacher teacher) {
        this.course_id = course_id;
        this.title = title;
        this.password = password;
        this.teacher = teacher;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Teacher_id")
    private Teacher teacher;





}
