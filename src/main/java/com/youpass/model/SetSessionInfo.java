package com.youpass.model;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class SetSessionInfo {
    private Long courseId;
    private Long examId;

    public SetSessionInfo(Long courseId, Long examId) {
        this.courseId = courseId;
        this.examId = examId;
    }

    public SetSessionInfo() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }
}
