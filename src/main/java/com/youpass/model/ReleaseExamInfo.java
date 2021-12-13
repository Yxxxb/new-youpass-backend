package com.youpass.model;

import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.ExamId;

import java.util.Date;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class ReleaseExamInfo {
    private Long courseId;
    private String title;
    private Date start_time;
    private Date end_time;

    private Integer choice_num;
    private Integer multi_choice_num;
    private Integer completion_num;
    private Integer filled_num;

    private Integer choice_value;
    private Integer multi_choice_value;
    private Integer completion_value;
    private Integer filled_value;

    public ReleaseExamInfo() {
    }

    public ReleaseExamInfo(Long courseId, String title, Date start_time, Date end_time, Integer choice_num, Integer multi_choice_num, Integer completion_num, Integer filled_num, Integer choice_value, Integer multi_choice_value, Integer completion_value, Integer filled_value) {
        this.courseId = courseId;
        this.title = title;
        this.start_time = start_time;
        this.end_time = end_time;
        this.choice_num = choice_num;
        this.multi_choice_num = multi_choice_num;
        this.completion_num = completion_num;
        this.filled_num = filled_num;
        this.choice_value = choice_value;
        this.multi_choice_value = multi_choice_value;
        this.completion_value = completion_value;
        this.filled_value = filled_value;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getChoice_num() {
        return choice_num;
    }

    public void setChoice_num(Integer choice_num) {
        this.choice_num = choice_num;
    }

    public Integer getMulti_choice_num() {
        return multi_choice_num;
    }

    public void setMulti_choice_num(Integer multi_choice_num) {
        this.multi_choice_num = multi_choice_num;
    }

    public Integer getCompletion_num() {
        return completion_num;
    }

    public void setCompletion_num(Integer completion_num) {
        this.completion_num = completion_num;
    }

    public Integer getFilled_num() {
        return filled_num;
    }

    public void setFilled_num(Integer filled_num) {
        this.filled_num = filled_num;
    }

    public Integer getChoice_value() {
        return choice_value;
    }

    public void setChoice_value(Integer choice_value) {
        this.choice_value = choice_value;
    }

    public Integer getMulti_choice_value() {
        return multi_choice_value;
    }

    public void setMulti_choice_value(Integer multi_choice_value) {
        this.multi_choice_value = multi_choice_value;
    }

    public Integer getCompletion_value() {
        return completion_value;
    }

    public void setCompletion_value(Integer completion_value) {
        this.completion_value = completion_value;
    }

    public Integer getFilled_value() {
        return filled_value;
    }

    public void setFilled_value(Integer filled_value) {
        this.filled_value = filled_value;
    }
}
