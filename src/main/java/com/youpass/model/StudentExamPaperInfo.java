package com.youpass.model;

public class StudentExamPaperInfo {
    private Long studentId;
    private Integer numInPaper;
    private String studentAnswer;
    private Integer value;


    public StudentExamPaperInfo() {
    }

    public StudentExamPaperInfo(Long studentId, Integer numInPaper, String studentAnswer, Integer value) {
        this.studentId = studentId;
        this.numInPaper = numInPaper;
        this.studentAnswer = studentAnswer;
        this.value = value;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getNumInPaper() {
        return numInPaper;
    }

    public void setNumInPaper(Integer numInPaper) {
        this.numInPaper = numInPaper;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StudentExamPaperInfo{" +
                "studentId='" + studentId + '\'' +
                ", numInPaper=" + numInPaper +
                ", studentAnswer='" + studentAnswer + '\'' +
                '}';
    }
}
