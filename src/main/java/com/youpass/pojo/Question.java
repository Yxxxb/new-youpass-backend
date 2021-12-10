package com.youpass.pojo;

import com.youpass.pojo.pk.QuestionId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Question implements Serializable {
    @EmbeddedId
    private QuestionId id;


    @Column(length = 1024, name = "description")
    private String description;
    @Column(length = 1024, name = "standard_answer")
    private String standard_answer;
    @Column(name = "type")
    private Integer type;
    @Column(length = 64, name = "subject")
    private String subject;
    @Column(name = "create_time")
    private Date create_time;

    // 不能加cascade 因为不想删除question就删除teacher
    @ManyToOne
    @JoinColumn(name = "Teacher_id",referencedColumnName = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "Course_id",referencedColumnName = "course_id")
    private Course course;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Option> optionSet = new HashSet<>();

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ExaminationPaper> examinationPaperSet = new HashSet<>();

    public Question() {
    }

}
