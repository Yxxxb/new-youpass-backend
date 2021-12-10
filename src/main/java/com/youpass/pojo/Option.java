package com.youpass.pojo;

import com.youpass.pojo.pk.OptionId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "question_option")
public class Option implements Serializable {
    @EmbeddedId
    private OptionId id;

    @Column(length = 256,name = "content")
    private String content;

    @MapsId("questionId")
    @ManyToOne
    @JoinColumn(name="question_id",referencedColumnName = "question_id")
    private Question question;

    public Option() {
    }
}
