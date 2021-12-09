package com.youpass.pojo;

import com.sun.xml.txw2.annotation.XmlCDATA;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Course {
    @Id
    @Column(name = "course_id")
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
    private Long id;
    @Column(length = 64, name = "title")
    private String title;
    @Column(length = 32, name = "password")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Course(Long id, String title, String password, Teacher teacher) {
        this.id = id;
        this.title = title;
        this.password = password;
        this.teacher = teacher;
    }

    public Course() {
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Teacher_id")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_course",
    joinColumns = {@JoinColumn(name = "course_id")},
    inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<Student> students;
}
