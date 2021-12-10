package com.youpass.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Student implements Serializable {
    @Id
    @SequenceGenerator(
            name = "Student_Sequence",
            sequenceName = "Student_Sequence",
            initialValue = 1950000,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Student_Sequence"
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
    private Set<Exam> users;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

    public Student() {
    }
}
