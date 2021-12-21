package com.youpass.model;

import com.youpass.pojo.Exam;
import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.ExamId;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class ExamReturnInfo implements Serializable {
    private Long courseId;
    private Long exam_id;
    private Date start_time;
    private Date end_time;
    private String title;

    public ExamReturnInfo() {
    }

    public ExamReturnInfo(Long courseId, Long exam_id, Date start_time, Date end_time, String title) {
        this.courseId = courseId;
        this.exam_id = exam_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.title = title;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getExam_id() {
        return exam_id;
    }

    public void setExam_id(Long exam_id) {
        this.exam_id = exam_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExam(Exam exam) {
        this.exam_id = exam.getId().getExamId();
        this.courseId = exam.getId().getCourseId();
        this.start_time = exam.getStart_time();
        this.end_time = exam.getEnd_time();
        this.title = exam.getTitle();
    }
}
