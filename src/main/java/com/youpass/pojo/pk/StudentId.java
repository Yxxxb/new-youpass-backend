package com.youpass.pojo.pk;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class StudentId implements Serializable {
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", initialValue = 1950000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @Column(name = "student_id")
    private Long studentId;

    public StudentId() {
    }

    public static StudentId.Builder Builder(){
        return new Builder();
    }

    public static class Builder{
        private Long studentId;

        public Builder setStudentId(Long studentId) {
            this.studentId = studentId;
            return this;
        }

        public StudentId build(){
            var studentId = new StudentId();
            studentId.studentId = this.studentId;
            return studentId;
        }
    }

    public Long getStudentId() {
        return studentId;
    }

    public StudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }


}
