package com.youpass.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnore
    @MapsId("courseId")
    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "course_id")
    private Course course;

    public Notice() {
        id = new NoticeId();
    }

    public static Notice.Builder Builder(){
        return new Builder();
    }

    public static class Builder{
        private NoticeId id;
        private String content;
        private Date time;
        private Course course;

        Builder(){
            id = new NoticeId();
        }

        public Builder setId(NoticeId id) {
            this.id = id;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setTime(Date time) {
            this.time = time;
            return this;
        }

        public Builder setCourse(Course course) {
            this.course = course;
            return this;
        }

        public Notice build(){
            var notice = new Notice();
            notice.id = id;
            notice.content = content;
            notice.time = time;
            notice.course = course;
            return notice;
        }
    }
}
