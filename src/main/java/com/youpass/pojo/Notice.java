package com.youpass.pojo;

import javax.persistence.Column;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table
public class Notice implements Serializable {
    @Id
    @Column(name="course_id", insertable=false,updatable=false)
    private Long course_id;

    @Id
    @Column(name="notice_id")
    @SequenceGenerator(
            name = "Notice_Sequence",
            sequenceName = "Notice_Sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Notice_Sequence"
    )
    private Long notice_id;

    @Column(length = 1024,name = "content")
    private String content;
    @Column(name="time")
    private Date time;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="course_id")
    private Course course;


    public Long getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(Long notice_id) {
        this.notice_id = notice_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Notice() {
    }

    public Notice(Long course_id, Long notice_id, String content, Date time, Course course) {
        this.course_id = course_id;
        this.notice_id = notice_id;
        this.content = content;
        this.time = time;
        this.course = course;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Notice(Long notice_id, String content, Date time, Course course) {

        this.notice_id = notice_id;
        this.content = content;
        this.time = time;
        this.course = course;
    }
}
