package com.youpass.pojo;

import com.youpass.pojo.pk.ExaminationPaperId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Student_Exam_Question")
public class ExaminationPaper implements Serializable {
    @EmbeddedId
    private ExaminationPaperId id;

    @Column(name = "self_Order")
    private int selfOrder;
    @Column(name = "Stu_Answer", length = 1024)
    private String stuAnswer;
    @Column(name = "value")
    private int value;
    @Column(name = "stu_point")
    private int stuPoint;
    @Column(name = "numinpaper")
    private int numInPaper;

    @MapsId("studentId")
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    //exam有两个主码，这里必须将两个主码都添加进来
    @MapsId("examId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "exam_id", referencedColumnName = "exam_id"),
            @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    })
    private Exam exam;

    @MapsId("questionId")
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

}
