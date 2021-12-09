package com.youpass.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class Question implements Serializable {
    @Id
    @Column(name = "question_id")
    @SequenceGenerator(
            name = "Question_Sequence",
            sequenceName = "Question_Sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Question_Sequence"
    )
    private Long id;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Teacher_id")
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Course_id")
    private Course course;

    public Question() {
    }

    public Question(Long id, String description, String standard_answer, Integer type, String subject, Date create_time, Teacher teacher, Course course) {
        this.id = id;
        this.description = description;
        this.standard_answer = standard_answer;
        this.type = type;
        this.subject = subject;
        this.create_time = create_time;
        this.teacher = teacher;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
