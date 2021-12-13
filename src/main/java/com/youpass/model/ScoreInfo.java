package com.youpass.model;

public class ScoreInfo {
    private Long studentId;
    private String name;
    private Integer score;

    public ScoreInfo() {
    }

    public ScoreInfo(Long studentId, String name, Integer score) {
        this.studentId = studentId;
        this.name = name;
        this.score = score;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
