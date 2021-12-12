package com.youpass.model.ReturnType;

import com.youpass.model.QuestionInfo;
import com.youpass.model.StudentExamPaperInfo;
import com.youpass.pojo.ExaminationPaper;
import com.youpass.pojo.Option;
import com.youpass.pojo.Question;
import com.youpass.pojo.Student;

import java.util.List;
import java.util.Set;

public class QuestionStuReturn {


    Set<StudentExamPaperInfo> studentList;
    QuestionInfo questionInfo;

    public QuestionStuReturn(Set<StudentExamPaperInfo> studentList, QuestionInfo questionInfo) {
        this.studentList = studentList;
        this.questionInfo = questionInfo;
    }

    public QuestionStuReturn() {
    }

    public Set<StudentExamPaperInfo> getStudentList() {
        return studentList;
    }

    public void setStudentList(Set<StudentExamPaperInfo> studentList) {
        this.studentList = studentList;
    }

    public QuestionInfo getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(QuestionInfo questionInfo) {
        this.questionInfo = questionInfo;
    }

    @Override
    public String toString() {
        return "QuestionStuReturn{" +
                "studentList=" + studentList +
                ", questionInfo=" + questionInfo +
                '}';
    }
}



