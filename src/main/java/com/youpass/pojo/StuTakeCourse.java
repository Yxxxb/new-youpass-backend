package com.youpass.pojo;

import com.youpass.pojo.pk.StuTakeCourseId;
import com.youpass.pojo.pk.StudentId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Student_Course")
public class StuTakeCourse implements Serializable {
    @EmbeddedId
    private StuTakeCourseId id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @MapsId("studentId")
    private Student student;

    @ManyToOne
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
}
