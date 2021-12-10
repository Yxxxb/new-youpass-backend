package com.youpass.pojo;

import com.youpass.pojo.pk.ExamId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Exam implements Serializable {
    @EmbeddedId
    private ExamId id;

    @Column(name = "start_time")
    private Date start_time;
    @Column(name = "end_time")
    private Date end_time;
    @Column(name = "nature")
    private Integer nature;
    @Column(length = 64, name = "title")
    private String title;
    @Column(name = "choice_num")
    private Integer choiceNum;
    @Column(name = "multi_choice_num")
    private Integer multiChoiceNum;
    @Column(name = "fill_num")
    private Integer filledNum;
    @Column(name = "completion_num")
    private Integer completionNum;


    @MapsId("courseId")
    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "course_id")
    private Course course;

    @OneToMany(mappedBy = "exam",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ExaminationPaper> examinationPaperSet = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_exam",
            //joinColumns,当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name = "exam_id"), @JoinColumn(name = "course_id")},
            //inverseJoinColumns，对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<Student> students;

    public Exam() {
    }
}
