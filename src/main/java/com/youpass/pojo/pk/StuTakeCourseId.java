package com.youpass.pojo.pk;

import com.youpass.pojo.StuTakeCourse;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
public class StuTakeCourseId implements Serializable {
    @Embedded
    private StudentId studentId;
    @Embedded
    private CourseId courseId;

    public StudentId getStudentId() {
        return studentId;
    }

    public void setStudentId(StudentId studentId) {
        this.studentId = studentId;
    }

    public CourseId getCourseId() {
        return courseId;
    }

    public void setCourseId(CourseId courseId) {
        this.courseId = courseId;
    }

    public StuTakeCourseId() {
        courseId= new CourseId();
        studentId = new StudentId();
    }

    public StuTakeCourseId(Long studentId, Long courseId) {
        this.studentId = new StudentId(studentId);
        this.courseId = new CourseId(courseId);
    }

    public StuTakeCourseId(StudentId studentId, CourseId courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

//    public static StuTakeCourseId.Builder Builder(){
//        return new Builder();
//    }
//    public static class Builder {
//        private StudentId studentId;
//        private CourseId courseId;
//
//        public Builder setStudentId(Long studentId) {
//            this.studentId = new StudentId(studentId);
//            return this;
//        }
//
//        public Builder setCourseId(Long courseId) {
//            this.courseId = new CourseId(courseId);
//            return this;
//        }
//
//        public StuTakeCourseId build() {
//            StuTakeCourseId stuTakeCourseId = new StuTakeCourseId();
//            stuTakeCourseId.studentId = this.studentId;
//            stuTakeCourseId.courseId = this.courseId;
//            return stuTakeCourseId;
//        }
//    }
}
