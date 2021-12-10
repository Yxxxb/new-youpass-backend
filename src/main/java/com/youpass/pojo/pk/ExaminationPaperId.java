package com.youpass.pojo.pk;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
public class ExaminationPaperId implements Serializable {
    @Embedded
    private StudentId studentId;

    @Embedded
    private ExamId examId;

    @Embedded
    private QuestionId questionId;

    public ExaminationPaperId() {
    }

    public ExaminationPaperId(Long studentId, Long examId,Long courseId, Long questionId) {
        this.studentId = StudentId.Builder().setStudentId(studentId).build();
        this.examId = new ExamId(examId,courseId);
        this.questionId = new QuestionId(questionId);
    }

    public Long getStudentId() {
        return studentId.getStudentId();
    }

    public void setStudentId(Long studentId) {
        this.studentId =  StudentId.Builder().setStudentId(studentId).build();
    }

    public Long getExamId() {
        return examId.getExamId();
    }

    public Long getCourseId() {
        return examId.getCourseId();
    }

    public void setExamId(Long examId,Long courseId) {
        this.examId = new ExamId(examId,courseId);
    }

    public Long getQuestionId() {
        return questionId.getQuestionId();
    }

    public void setQuestionId(Long questionId) {
        this.questionId = new QuestionId(questionId);
    }
}
