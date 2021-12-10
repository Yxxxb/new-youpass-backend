package com.youpass.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Question_Option")
@Table
public class QuestionOption implements Serializable {
    @Id
    @Column(name="question_id", insertable=false,updatable=false)
    private Long question_id;

    @Id
    @Column(name="option_id")
    @SequenceGenerator(
            name = "Option_Sequence",
            sequenceName = "Option_Sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Option_Sequence"
    )
    private Long option_id;

    @Column(length = 256,name = "content")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="question_id", insertable=false, updatable=false)
    private Question question;

    public QuestionOption() {
    }

}
