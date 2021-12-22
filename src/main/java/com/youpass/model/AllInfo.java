package com.youpass.model;

import com.youpass.model.ExamReturnInfo;
import com.youpass.model.UserInfo;
import com.youpass.pojo.Course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class AllInfo implements Serializable {
    private UserInfo userInfo;
    private Set<CourseInfo> courseList = new HashSet<>();
    private Set<CourseDetail> courseListStu = new HashSet<>();
    private List<ExamReturnInfo> examList = new ArrayList<>();
    private Set<NoticeInfo> noticeInfoSet = new HashSet<>();
//    private List<List<ExamReturnInfo>> courseExamList;

    public AllInfo() {
    }

    public AllInfo(UserInfo userInfo, Set<CourseInfo> courseList) {
        this.userInfo = userInfo;
        this.courseList = courseList;
    }


    public AllInfo(UserInfo userInfo, Set<CourseDetail> courseListStu, List<ExamReturnInfo> examList) {
        this.userInfo = userInfo;
        this.courseListStu = courseListStu;
        this.examList = examList;
    }

    public AllInfo(UserInfo userInfo, Set<CourseInfo> courseList, Set<CourseDetail> courseListStu, List<ExamReturnInfo> examList) {
        this.userInfo = userInfo;
        this.courseList = courseList;
        this.courseListStu = courseListStu;
        this.examList = examList;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Set<CourseInfo> getCourseList() {
        return courseList;
    }

    public void setCourseList(Set<CourseInfo> courseList) {
        this.courseList = courseList;
    }

    public Set<CourseDetail> getCourseListStu() {
        return courseListStu;
    }

    public void setCourseListStu(Set<CourseDetail> courseListStu) {
        this.courseListStu = courseListStu;
    }

    public List<ExamReturnInfo> getExamList() {
        return examList;
    }

    public void setExamList(List<ExamReturnInfo> examList) {
        this.examList = examList;
    }

    public Set<NoticeInfo> getNoticeInfoSet() {
        return noticeInfoSet;
    }

    public void setNoticeInfoSet(Set<NoticeInfo> noticeInfoSet) {
        this.noticeInfoSet = noticeInfoSet;
    }
}
