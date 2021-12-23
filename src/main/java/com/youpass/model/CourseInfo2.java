package com.youpass.model;

import com.youpass.pojo.Course;

import java.util.HashSet;
import java.util.Set;

public class CourseInfo2 {
    private String title;
    private String password;

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

    public CourseInfo2(String title, String password) {
        this.title = title;
        this.password = password;
    }

    public CourseInfo2() {
    }
}
