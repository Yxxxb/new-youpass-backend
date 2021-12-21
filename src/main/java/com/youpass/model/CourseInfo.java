package com.youpass.model;

import com.youpass.pojo.Course;

import java.util.HashSet;
import java.util.Set;

public class CourseInfo {
    private String title;
    private String password;
    private Long courseId;

    private Set<ExamReturnInfo> examReturnInfoSet = new HashSet<>();

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

    public Set<ExamReturnInfo> getExamReturnInfoSet() {
        return examReturnInfoSet;
    }

    public void setExamReturnInfoSet(Set<ExamReturnInfo> examReturnInfoSet) {
        this.examReturnInfoSet = examReturnInfoSet;
    }

    public void setCourse(Course course){
        this.courseId = course.getId().getCourseId();
        this.title = course.getTitle();
        for(var exam:course.getExamSet()){
            ExamReturnInfo examReturnInfo = new ExamReturnInfo();
            examReturnInfo.setExam(exam);
        }
    }
}
