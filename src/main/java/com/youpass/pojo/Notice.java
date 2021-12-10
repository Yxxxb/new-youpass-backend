package com.youpass.pojo;

import com.youpass.pojo.pk.NoticeId;

import javax.persistence.Column;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class Notice implements Serializable {
    @EmbeddedId
    private NoticeId id;

    @Column(length = 1024, name = "content")
    private String content;
    @Column(name = "time")
    private Date time;

    @MapsId("courseId")
    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "course_id")
    private Course course;

    public Notice() {
    }
}
