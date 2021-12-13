package com.youpass.model;

import com.youpass.pojo.pk.NoticeId;

import java.util.Date;

public class NoticeInfo {
    private Long noticeId;
    private Long courseId;
    private Long teacherId;
    private String content;
    private Date time;
    private String courseTitle;
    private String teacherName;

    public NoticeInfo() {
    }

    public NoticeInfo(Long noticeId, Long courseId, Long teacherId, String content, Date time, String courseTitle, String teacherName) {
        this.noticeId = noticeId;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.content = content;
        this.time = time;
        this.courseTitle = courseTitle;
        this.teacherName = teacherName;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
