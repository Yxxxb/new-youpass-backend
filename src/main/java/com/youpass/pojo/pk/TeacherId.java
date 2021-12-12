package com.youpass.pojo.pk;

import com.youpass.pojo.Teacher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeacherId implements Serializable {
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Teacher_Sequence"
    )
    @SequenceGenerator(
            name = "Teacher_Sequence",
            sequenceName = "Teacher_Sequence",
            initialValue = 10500,
            allocationSize = 1
    )
    @Column(name = "teacher_id")
    private Long teacherId;

    // 不对外提供初始化Teacher id的方法
    public TeacherId() {
    }

    public TeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

//    public static TeacherId.Builder Builder(){
//        return new Builder();
//    }
//
//    public static class Builder{
//        private Long teacherId;
//
//        public Builder setTeacherId(Long teacherId) {
//            this.teacherId = teacherId;
//            return this;
//        }
//
//        public TeacherId build(){
//            var teacherId = new TeacherId();
//            teacherId.teacherId = this.teacherId;
//            return teacherId;
//        }
//    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherId teacherId1 = (TeacherId) o;
        return Objects.equals(teacherId, teacherId1.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId);
    }
}
