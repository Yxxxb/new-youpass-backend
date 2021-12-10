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

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ExaminationPaper> examinationPaperSet = new HashSet<>();
    //cascade = CascadeType.ALL待定
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ExamInfo> examInfos = new HashSet<>();

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

    public Student() {
    }
}
