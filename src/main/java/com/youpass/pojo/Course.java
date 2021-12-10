package com.youpass.pojo;

import com.sun.xml.txw2.annotation.XmlCDATA;
import com.youpass.pojo.pk.CourseId;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Course implements Serializable {
    @EmbeddedId
    private CourseId id;

    @Column(length = 64, name = "title")
    private String title;
    @Column(length = 32, name = "password")
    private String password;

    //删除课程不能删除老师，所以不能加cascade
    @MapsId("teacherId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Notice> noticeList = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_course",
    joinColumns = {@JoinColumn(name = "course_id")},
    inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<Student> students;

    public Course() {
    }
}
