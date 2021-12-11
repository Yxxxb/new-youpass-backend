package com.youpass.pojo;

import com.youpass.pojo.pk.ExamInfoId;
import com.youpass.pojo.pk.StudentId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Student_Exam")
public class ExamInfo implements Serializable {
    @EmbeddedId
    private ExamInfoId id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @MapsId("studentId")
    private Student student;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "exam_id", referencedColumnName = "exam_id"),
            @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    })
    @MapsId("examId")
    private Exam exam;

    @Column(name = "score")
    private Integer score;
    @Column(name = "state")
    private Integer state;

    public ExamInfo() {
    }

    public static ExamInfo.Builder Builder(){
        return new Builder();
    }

    public static class Builder{
        private ExamInfoId id= new ExamInfoId();
        private Student student;
        private Exam exam;
        private Integer score;
        private Integer state;

        public Builder setId(ExamInfoId id) {
            this.id = id;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder setExam(Exam exam) {
            this.exam = exam;
            return this;
        }

        public Builder setScore(Integer score) {
            this.score = score;
            return this;
        }

        public Builder setState(Integer state) {
            this.state = state;
            return this;
        }

        public ExamInfo build(){
            var examInfo = new ExamInfo();
            examInfo.id = id;
            examInfo.student = student;
            examInfo.exam = exam;
            examInfo.score = score;
            examInfo.state = state;
            return examInfo;
        }
    }

    public ExamInfoId getId() {
        return id;
    }

    public void setId(ExamInfoId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
