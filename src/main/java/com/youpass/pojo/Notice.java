package com.youpass.pojo;

import javax.persistence.Column;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table
public class Notice {
    @Id
    @Column(name="course_id")
    private Long courseId;

    @Column(name="notice_id")
    private Long notice_id;
    @Column(length = 1024,name = "content")
    private String content;
    @Column(name="time")
    private Date time;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="course_id", referencedColumnName = "course_id")
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

    public Notice(String content, Date time, Course course) {
        this.content = content;
        this.time = time;
        this.course = course;
    }

    public Notice(Long notice_id, String content, Date time, Course course) {
        this.notice_id = notice_id;
        this.content = content;
        this.time = time;
        this.course = course;
    }
}
