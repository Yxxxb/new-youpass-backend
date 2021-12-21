package com.youpass.model;

import com.youpass.model.ExamReturnInfo;
import com.youpass.model.UserInfo;
import com.youpass.pojo.Course;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class AllInfo implements Serializable {
    private UserInfo userInfo;
    private Set<Course> courseList;
    private List<ExamReturnInfo> examList;
//    private List<List<ExamReturnInfo>> courseExamList;

    public AllInfo(UserInfo userInfo, Set<Course> courseList) {
        this.userInfo = userInfo;
        this.courseList = courseList;
    }

    public AllInfo() {
    }

    public AllInfo(UserInfo userInfo, Set<Course> courseList, List<ExamReturnInfo> examList) {
        this.userInfo = userInfo;
        this.courseList = courseList;
        this.examList = examList;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Set<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(Set<Course> courseList) {
        this.courseList = courseList;
    }

    public List<ExamReturnInfo> getExamList() {
        return examList;
    }

    public void setExamList(List<ExamReturnInfo> examList) {
        this.examList = examList;
    }

    @Override
    public String toString() {
        return "AllInfo{" +
                "userInfo=" + userInfo +
                ", courseList=" + courseList +
                ", examList=" + examList +
                '}';
    }
}
