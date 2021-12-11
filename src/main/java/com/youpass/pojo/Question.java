package com.youpass.pojo;

import com.youpass.pojo.pk.QuestionId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Question implements Serializable {
    @EmbeddedId
    private QuestionId id;


    @Column(length = 1024, name = "description")
    private String description;
    @Column(length = 1024, name = "standard_answer")
    private String standard_answer;
    @Column(name = "type")
    private Integer type;
    @Column(length = 64, name = "subject")
    private String subject;
    @Column(name = "create_time")
    private Date create_time;




    @ManyToOne
    @JoinColumn(name = "Course_id",referencedColumnName = "course_id")
    private Course course;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private Set<Option> optionSet = new HashSet<>();

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private Set<ExaminationPaper> examinationPaperSet = new HashSet<>();

    public Question() {
    }

    public static Question.Builder Builder(){
        return new Builder();
    }

    public static class Builder{
        private QuestionId id;
        private String description;
        private String standard_answer;
        private Integer type;
        private String subject;
        private Date create_time;
        private Course course;

        public Builder setId(QuestionId id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setStandard_answer(String standard_answer) {
            this.standard_answer = standard_answer;
            return this;
        }

        public Builder setType(Integer type) {
            this.type = type;
            return this;
        }

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder setCreate_time(Date create_time) {
            this.create_time = create_time;
            return this;
        }

        public Builder setCourse(Course course) {
            this.course = course;
            return this;
        }

        public Question build(){
            var question = new Question();
            question.id = id;
            question.description = description;
            question.standard_answer = standard_answer;
            question.type = type;
            question.subject = subject;
            question.create_time = create_time;
            question.course = course;
            return question;
        }
    }

    public QuestionId getId() {
        return id;
    }

    public void setId(QuestionId id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStandard_answer() {
        return standard_answer;
    }

    public void setStandard_answer(String standard_answer) {
        this.standard_answer = standard_answer;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Option> getOptionSet() {
        return optionSet;
    }

    public void setOptionSet(Set<Option> optionSet) {
        this.optionSet = optionSet;
    }

    public Set<ExaminationPaper> getExaminationPaperSet() {
        return examinationPaperSet;
    }

    public void setExaminationPaperSet(Set<ExaminationPaper> examinationPaperSet) {
        this.examinationPaperSet = examinationPaperSet;
    }
}
