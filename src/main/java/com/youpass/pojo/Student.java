package com.youpass.pojo;

import com.youpass.pojo.pk.StudentId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Student implements Serializable {
    @EmbeddedId
    private StudentId id;

    @Column(length = 16, name = "Name")
    private String name;
    @Column(length = 32, name = "Password")
    private String password;
    @Column(length = 32, name = "Email")
    private String email;
    @Column(length = 128, name = "Location")
    private String location;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ExaminationPaper> examinationPaperSet=new HashSet<>();

    // Set原因：主码不能重复，所以不能用List
    @ManyToMany(mappedBy = "students")
    private Set<Exam> exams;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

    public Student() {
    }
}
