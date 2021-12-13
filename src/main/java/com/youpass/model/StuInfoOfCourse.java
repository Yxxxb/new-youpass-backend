package com.youpass.model;

import com.youpass.pojo.pk.ExamId;

import java.util.ArrayList;
import java.util.List;

public class StuInfoOfCourse {
    //学生id和姓名
    private Long id;
    private String name;
    private List<StuInfoOfExam> examInfo = new ArrayList<>();

    public static class StuInfoOfExam{
        private Long examId;
        private Integer nature;
        private String title;
        private Integer score;
        private Integer state;

        public StuInfoOfExam() {
        }

        public Long getExamId() {
            return examId;
        }

        public void setExamId(Long examId) {
            this.examId = examId;
        }

        public Integer getNature() {
            return nature;
        }

        public void setNature(Integer nature) {
            this.nature = nature;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }
    }

    public StuInfoOfCourse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StuInfoOfExam> getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(List<StuInfoOfExam> examInfo) {
        this.examInfo = examInfo;
    }
}
