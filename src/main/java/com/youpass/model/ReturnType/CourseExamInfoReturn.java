package com.youpass.model.ReturnType;

public class CourseExamInfoReturn {
    private Long courseId;
    private Long examId;
    private String title;
    private Integer score;

    public CourseExamInfoReturn() {
    }

    public CourseExamInfoReturn(Long courseId, Long examId, String title, Integer score) {
        this.courseId = courseId;
        this.examId = examId;
        this.title = title;
        this.score = score;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
