package com.youpass.model;

import com.youpass.pojo.ExaminationPaper;
import com.youpass.pojo.Question;
import com.youpass.pojo.Student;

import java.util.List;
import java.util.Set;

public class QuestionStuReturn {


    Set<ExaminationPaper> examinationPaperSet;
    Question question;

    public QuestionStuReturn() {
    }
    public Set<ExaminationPaper> getExaminationPaperSet() {
        return examinationPaperSet;
    }

    public void setExaminationPaperSet(Set<ExaminationPaper> examinationPaperSet) {
        this.examinationPaperSet = examinationPaperSet;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionStuReturn(Set<ExaminationPaper> examinationPaperSet, Question question) {
        this.examinationPaperSet = examinationPaperSet;
        this.question = question;
    }

}


