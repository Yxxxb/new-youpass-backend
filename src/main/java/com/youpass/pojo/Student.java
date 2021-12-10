package com.youpass.pojo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            initialValue = 1950000,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    @Column(length = 16, name = "Name")
    private String name;
    @Column(length = 32, name = "Password")
    private String password;
    @Column(length = 32, name = "Email")
    private String email;
    @Column(length = 128 ,name="Location")
    private String location;

    //Set原因：主码不能重复，所以不能用List
    @ManyToMany(mappedBy = "students")
    private Set<Exam> exams;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Student(String name, String password, String email, String location) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.location = location;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> users) {
        this.exams = users;
    }



    public Student(Long id, String name, String password, String email, String location, Set<Exam> users) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.location = location;
        this.exams = users;
    }

    public Student() {
    }
}
