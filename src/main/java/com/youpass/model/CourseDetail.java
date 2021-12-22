package com.youpass.model;

import java.util.List;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class CourseDetail {
    private CourseInfo courseInfo;
    private Long teacherId;
    private String teacherName;
    List<ExamReturnInfo> exams;

    public CourseDetail(CourseInfo courseInfo, Long teacherId, String teacherName, List<ExamReturnInfo> exams) {
        this.courseInfo = courseInfo;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.exams = exams;
    }

    public CourseDetail() {
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<ExamReturnInfo> getExams() {
        return exams;
    }

    public void setExams(List<ExamReturnInfo> exams) {
        this.exams = exams;
    }
}
