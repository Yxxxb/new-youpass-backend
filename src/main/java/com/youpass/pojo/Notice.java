package com.youpass.pojo;

import com.youpass.pojo.pk.NoticeId;

import javax.persistence.Column;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class Notice implements Serializable {
    @EmbeddedId
    private NoticeId noticeId;

    @Column(length = 1024, name = "content")
    private String content;
    @Column(name = "time")
    private Date time;

    @MapsId("courseId")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Notice() {
    }

    public Notice(NoticeId noticeId, String content, Date time, Course course) {
        this.noticeId = noticeId;
        this.content = content;
        this.time = time;
        this.course = course;
    }

    public Notice(NoticeId noticeId, String content, Date time) {
        this.noticeId = noticeId;
        this.content = content;
        this.time = time;
    }

    public NoticeId getNoticeId() {
        return noticeId;
    }

    public Course getCourse() {
        return course;
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
}
