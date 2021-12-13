package com.youpass.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.youpass.pojo.pk.StuTakeCourseId;
import com.youpass.pojo.pk.StudentId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Student_Course")
public class StuTakeCourse implements Serializable {
    @EmbeddedId
    private StuTakeCourseId id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @MapsId("studentId")
    private Student student;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    @MapsId("courseId")
    private Course course;

    public StuTakeCourse(StuTakeCourseId id, Student student, Course course) {
        this.id = id;
        this.student = student;
        this.course = course;
    }

    public StuTakeCourse() {
        id = new StuTakeCourseId();
    }

    public static StuTakeCourse.Builder Builder(){
        return new Builder();
    }

    @Override
    public String toString() {
        return "StuTakeCourse{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                '}';
    }

    public static class Builder {
        private StuTakeCourseId id;
        private Student student;
        private Course course;

        Builder(){
            id = new StuTakeCourseId();
        }

        public Builder setId(StuTakeCourseId id) {
            this.id = id;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder setCourse(Course course) {
            this.course = course;
            return this;
        }

        public StuTakeCourse build(){
            StuTakeCourse stuTakeCourse=new StuTakeCourse();
            stuTakeCourse.course=course;
            stuTakeCourse.student=student;
            stuTakeCourse.id=id;
            return stuTakeCourse;
        }
    }

    public StuTakeCourseId getId() {
        return id;
    }

    public void setId(StuTakeCourseId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StuTakeCourse that = (StuTakeCourse) o;
        return Objects.equals(id, that.id) && Objects.equals(student, that.student) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, course);
    }
}
