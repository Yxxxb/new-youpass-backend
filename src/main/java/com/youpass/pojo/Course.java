package com.youpass.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.xml.txw2.annotation.XmlCDATA;
import com.youpass.pojo.pk.CourseId;
import org.aspectj.weaver.ast.Not;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
@JsonSerialize
public class Course implements Serializable {
    @EmbeddedId
    private CourseId id;

    @Column(length = 64, name = "title")
    private String title;
    @JsonIgnore
    @Column(length = 32, name = "password")
    private String password;

    @JsonIgnoreProperties(value = {"email", "location"})
    @ManyToOne
    @JoinColumn(name = "Teacher_id", referencedColumnName = "teacher_id")
    private Teacher teacher;

    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Notice> noticeSet = new HashSet<>();

    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Exam> examSet = new HashSet<>();

    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Question> questionSet = new HashSet<>();

    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<StuTakeCourse> stuTakeCourses = new HashSet<>();

    public Course() {
        id = new CourseId();
    }

    public static Course.Builder Builder() {
        return new Builder();
    }

    public static class Builder {
        private CourseId id;
        private String title;
        private String password;
        private Teacher teacher;
//        private Set<Notice> noticeSet = new HashSet<>();
//        private Set<Exam> examSet = new HashSet<>();
//        private Set<Question> questionSet = new HashSet<>();
//        private Set<StuTakeCourse> stuTakeCourses;

        Builder() {
            id = new CourseId();
        }

        public Builder setId(CourseId id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setTeacher(Teacher teacher) {
            this.teacher = teacher;
            return this;
        }

        public Course build() {
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

    public Set<StuTakeCourse> getStuTakeCourses() {
        return stuTakeCourses;
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

    public void setNoticeSet(Set<Notice> noticeSet) {
        this.noticeSet = noticeSet;
    }

    public void setExamSet(Set<Exam> examSet) {
        this.examSet = examSet;
    }

    public void setQuestionSet(Set<Question> questionSet) {
        this.questionSet = questionSet;
    }

    public void setStuTakeCourses(Set<StuTakeCourse> stuTakeCourses) {
        this.stuTakeCourses = stuTakeCourses;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
