package com.youpass.pojo.pk;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseId implements Serializable {

    @SequenceGenerator(
            name = "Course_Sequence",
            sequenceName = "Course_Sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Course_Sequence"
    )
    @Column(name = "course_id")
    private Long courseId;

    public CourseId() {
    }

    public CourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    public static CourseId.Builder Builder(){
        return new Builder();
    }
    public static class Builder{
        private Long courseId;

        public Builder setCourseId(Long courseId){
            this.courseId=courseId;
            return this;
        }
        public CourseId build(){
            CourseId courseId=new CourseId();
            courseId.courseId=this.courseId;
            return courseId;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseId courseId1 = (CourseId) o;
        return Objects.equals(courseId, courseId1.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}
