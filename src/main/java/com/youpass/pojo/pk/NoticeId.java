package com.youpass.pojo.pk;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class NoticeId implements Serializable {
    /**
     * Notice 主码为 自己的id和course id
     * 自己的id自动生成 course id 外部给出
     */

    @SequenceGenerator(
            name = "Notice_Sequence",
            sequenceName = "Notice_Sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Notice_Sequence"
    )
    @Column(name = "notice_id")
    private Long noticeId;

    @Embedded
    private CourseId courseId;

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public CourseId getCourseId() {
        return courseId;
    }

    public void setCourseId(CourseId courseId) {
        this.courseId = courseId;
    }

    public NoticeId(Long noticeId, CourseId courseId) {
        this.noticeId = noticeId;
        this.courseId = courseId;
    }

    public NoticeId() {
    }
    public static NoticeId.Builder Builder(){
        return new Builder();
    }
    public static class Builder{
        private Long noticeId;
        private CourseId courseId;
        public Builder getNoticeId(Long noticeId1){
            this.noticeId =noticeId1;
            return  this;
        }
        public Builder setCourseId(Long courseId){
            this.courseId.setCourseId(courseId);
            return this;
        }
        public NoticeId build(){
            NoticeId noticeId =new NoticeId();
            noticeId.courseId=this.courseId;
            noticeId.noticeId=this.noticeId;
            return noticeId;
        }
    }
}
