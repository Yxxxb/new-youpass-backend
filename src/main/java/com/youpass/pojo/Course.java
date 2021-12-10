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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Teacher_id",referencedColumnName = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Notice> noticeSet = new HashSet<>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Exam> examSet = new HashSet<>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Question> questionSet = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "student_course",
    joinColumns = {@JoinColumn(name = "course_id",referencedColumnName = "course_id")},
    inverseJoinColumns = {@JoinColumn(name = "student_id",referencedColumnName = "student_id")})
    private Set<Student> students;

    public Course() {
    }

    public static class Builder{
        private CourseId id;
        private String title;
        private String password;
        private Teacher teacher;
//        private Set<Notice> noticeSet = new HashSet<>();
//        private Set<Exam> examSet = new HashSet<>();
//        private Set<Question> questionSet = new HashSet<>();
//        private Set<Student> students;

        public Builder setId(CourseId id){
            this.id = id;
            return this;
        }

        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        public Builder setPassword(String password){
            this.password = password;
            return this;
        }

        public Builder setTeacher(Teacher teacher){
            this.teacher = teacher;
            return this;
        }

        public Course build(){
            var course = new Course();
            course.id = this.id;
            course.title = this.title;
            course.password = this.password;
            course.teacher = this.teacher;
            return course;
        }
    }

    public CourseId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPassword() {
        return password;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Set<Notice> getNoticeSet() {
        return noticeSet;
    }

    public Set<Exam> getExamSet() {
        return examSet;
    }

    public Set<Question> getQuestionSet() {
        return questionSet;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setId(CourseId id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
