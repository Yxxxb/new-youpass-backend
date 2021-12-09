package com.youpass.pojo;

import org.springframework.jdbc.core.SqlReturnType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "Teacher_Sequence",
            sequenceName = "Teacher_Sequence",
            initialValue = 10500,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Teacher_Sequence"
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
    @OneToMany(mappedBy = "teacher")
    private List<Course> courseList;

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public Teacher(String name, String password, String email, String location) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.location = location;
    }

    public Teacher(Long id, String name, String password, String email, String location) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.location = location;
    }

    public Teacher() {
    }

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
}
