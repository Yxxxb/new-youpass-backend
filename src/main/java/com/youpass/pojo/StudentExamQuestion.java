package com.youpass.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Student_Exam_Question")
@Table
public class StudentExamQuestion implements Serializable {
    @Id
    @Column(name = "student_id", insertable = false, updatable = false)
    private Long sid;

    @Id
    @Column(name = "course_id", insertable = false, updatable = false)
    private Long cid;

    @Id
    @Column(name = "exam_id", insertable = false, updatable = false)
    private Long eid;

    @Id
    @Column(name = "question_id", insertable = false, updatable = false)
    private Long qid;

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


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;


    //exam有两个主码，这里必须将两个主码都添加进来
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id", insertable = false, updatable = false)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Exam exam;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private Question question;

}
