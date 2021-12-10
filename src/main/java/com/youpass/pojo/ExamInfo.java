package com.youpass.pojo;

import com.youpass.pojo.pk.ExamInfoId;
import com.youpass.pojo.pk.StudentId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Student_Exam")
public class ExamInfo implements Serializable {
    @EmbeddedId
    private ExamInfoId id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @MapsId("studentId")
    private Student student;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "exam_id", referencedColumnName = "exam_id"),
            @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    })
    @MapsId("examId")
    private Exam exam;

    @Column(name = "score")
    private Integer score;
    @Column(name = "state")
    private Integer state;

    public ExamInfo() {
    }
}
