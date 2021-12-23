package com.youpass.model;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class CourseInfo3 {
    private String title;
    private String password;
    private Long courseId;

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

    public CourseInfo3(String title, String password, Long courseId) {
        this.title = title;
        this.password = password;
        this.courseId = courseId;
    }

    public CourseInfo3() {
    }
}
