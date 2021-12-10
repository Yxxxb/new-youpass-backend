package com.youpass.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class Question implements Serializable {
    @Id
    @Column(name = "question_id")
    @SequenceGenerator(
            name = "Question_Sequence",
            sequenceName = "Question_Sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Question_Sequence"
    )
    private Long id;

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
    @JoinColumn(name = "Teacher_id")
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Course_id")
    private Course course;

    public Question() {
    }

}
