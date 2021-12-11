package com.youpass.pojo;

import com.youpass.pojo.pk.ExamId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Exam implements Serializable {
    @EmbeddedId
    private ExamId id;

    @Column(name = "start_time")
    private Date start_time;
    @Column(name = "end_time")
    private Date end_time;
    @Column(name = "nature")
    private Integer nature;
    @Column(length = 64, name = "title")
    private String title;
    @Column(name = "choice_num")
    private Integer choiceNum;
    @Column(name = "multi_choice_num")
    private Integer multiChoiceNum;
    @Column(name = "fill_num")
    private Integer filledNum;
    @Column(name = "completion_num")
    private Integer completionNum;


    @MapsId("courseId")
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private Set<ExaminationPaper> examinationPaperSet = new HashSet<>();

    //cascade = CascadeType.ALL待定
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private Set<ExamInfo> examInfoSet = new HashSet<>();

    public Exam() {
    }

    public static Exam.Builder Builder(){
        return new Builder();
    }

    public static class Builder{
        private ExamId id;
        private Date start_time;
        private Date end_time;
        private Integer nature;
        private String title;
        private Integer choiceNum;
        private Integer multiChoiceNum;
        private Integer filledNum;
        private Integer completionNum;
        private Course course;

        public Builder setCourse(Course course) {
            this.course = course;
            return this;
        }

        public Builder setId(ExamId id) {
            this.id = id;
            return this;
        }

        public Builder setStart_time(Date start_time) {
            this.start_time = start_time;
            return this;
        }

        public Builder setEnd_time(Date end_time) {
            this.end_time = end_time;
            return this;
        }

        public Builder setNature(Integer nature) {
            this.nature = nature;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setChoiceNum(Integer choiceNum) {
            this.choiceNum = choiceNum;
            return this;
        }

        public Builder setMultiChoiceNum(Integer multiChoiceNum) {
            this.multiChoiceNum = multiChoiceNum;
            return this;
        }

        public Builder setFilledNum(Integer filledNum) {
            this.filledNum = filledNum;
            return this;
        }

        public Builder setCompletionNum(Integer completionNum) {
            this.completionNum = completionNum;
            return this;
        }

        public Exam build(){
            var exam = new Exam();
            exam.id = id;
            exam.start_time = start_time;
            exam.end_time = end_time;
            exam.nature = nature;
            exam.title = title;
            exam.choiceNum = choiceNum;
            exam.multiChoiceNum = multiChoiceNum;
            exam.filledNum = filledNum;
            exam.completionNum = completionNum;
            exam.course = course;
            return exam;
        }
    }

    public ExamId getId() {
        return id;
    }

    public void setId(ExamId id) {
        this.id = id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getChoiceNum() {
        return choiceNum;
    }

    public void setChoiceNum(Integer choiceNum) {
        this.choiceNum = choiceNum;
    }

    public Integer getMultiChoiceNum() {
        return multiChoiceNum;
    }

    public void setMultiChoiceNum(Integer multiChoiceNum) {
        this.multiChoiceNum = multiChoiceNum;
    }

    public Integer getFilledNum() {
        return filledNum;
    }

    public void setFilledNum(Integer filledNum) {
        this.filledNum = filledNum;
    }

    public Integer getCompletionNum() {
        return completionNum;
    }

    public void setCompletionNum(Integer completionNum) {
        this.completionNum = completionNum;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<ExaminationPaper> getExaminationPaperSet() {
        return examinationPaperSet;
    }

    public void setExaminationPaperSet(Set<ExaminationPaper> examinationPaperSet) {
        this.examinationPaperSet = examinationPaperSet;
    }

    public Set<ExamInfo> getExamInfoSet() {
        return examInfoSet;
    }

    public void setExamInfoSet(Set<ExamInfo> examInfoSet) {
        this.examInfoSet = examInfoSet;
    }
}
