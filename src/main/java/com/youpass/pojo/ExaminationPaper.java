package com.youpass.pojo;

import com.youpass.pojo.pk.ExaminationPaperId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Student_Exam_Question")
public class ExaminationPaper implements Serializable {
    @EmbeddedId
    private ExaminationPaperId id;

    @Column(name = "self_Order")
    private int selfOrder;
    @Column(name = "Stu_Answer", length = 1024)
    private String stuAnswer;
    @Column(name = "value")
    private int value;
    @Column(name = "stu_point")
    private int stuPoint;
    @Column(name = "numinpaper")
    private int numInPaper;

    @MapsId("studentId")
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    //exam有两个主码，这里必须将两个主码都添加进来
    @MapsId("examId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "exam_id", referencedColumnName = "exam_id"),
            @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    })
    private Exam exam;

    @MapsId("questionId")
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    public ExaminationPaper() {
    }

    public static ExaminationPaper.Builder Builder(){
        return new Builder();
    }

    public static class Builder{
        private ExaminationPaperId id;
        private int selfOrder;
        private String stuAnswer;
        private int value;
        private int stuPoint;
        private int numInPaper;
        private Student student;
        private Exam exam;
        private Question question;

        public Builder setId(ExaminationPaperId id) {
            this.id = id;
            return this;
        }

        public Builder setSelfOrder(int selfOrder) {
            this.selfOrder = selfOrder;
            return this;
        }

        public Builder setStuAnswer(String stuAnswer) {
            this.stuAnswer = stuAnswer;
            return this;
        }

        public Builder setValue(int value) {
            this.value = value;
            return this;
        }

        public Builder setStuPoint(int stuPoint) {
            this.stuPoint = stuPoint;
            return this;
        }

        public Builder setNumInPaper(int numInPaper) {
            this.numInPaper = numInPaper;
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

        public Builder setQuestion(Question question) {
            this.question = question;
            return this;
        }

        public ExaminationPaper build(){
            var examinationPaper = new ExaminationPaper();
            examinationPaper.id = id;
            examinationPaper.selfOrder = selfOrder;
            examinationPaper.stuAnswer = stuAnswer;
            examinationPaper.value = value;
            examinationPaper.stuPoint = stuPoint;
            examinationPaper.numInPaper = numInPaper;
            examinationPaper.student = student;
            examinationPaper.exam = exam;
            examinationPaper.question = question;
            return examinationPaper;
        }
    }

    public ExaminationPaperId getId() {
        return id;
    }

    public void setId(ExaminationPaperId id) {
        this.id = id;
    }

    public int getSelfOrder() {
        return selfOrder;
    }

    public void setSelfOrder(int selfOrder) {
        this.selfOrder = selfOrder;
    }

    public String getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(String stuAnswer) {
        this.stuAnswer = stuAnswer;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getStuPoint() {
        return stuPoint;
    }

    public void setStuPoint(int stuPoint) {
        this.stuPoint = stuPoint;
    }

    public int getNumInPaper() {
        return numInPaper;
    }

    public void setNumInPaper(int numInPaper) {
        this.numInPaper = numInPaper;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
