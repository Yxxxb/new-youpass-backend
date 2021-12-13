package com.youpass.model;

public class CourseInfo {
    private String title;
    private String password;
    private Long courseId;

    public CourseInfo() {
    }

    public CourseInfo(String title, String password, Long courseId) {
        this.title = title;
        this.password = password;
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
