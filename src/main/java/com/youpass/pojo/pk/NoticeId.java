package com.youpass.pojo.pk;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class NoticeId implements Serializable {
    /**
     * Notice 主码为 自己的id和course id
     * 自己的id自动生成 course id 外部给出
     */

//    @SequenceGenerator(
//            name = "Notice_Sequence",
//            sequenceName = "Notice_Sequence",
//            initialValue = 1,
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "Notice_Sequence"
//    )
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

    public Long getCourseId() {
        return courseId.getCourseId();
    }

    public void setCourseId(Long courseId) {
        this.courseId = new CourseId(courseId);
    }

    public NoticeId(Long noticeId, CourseId courseId) {
        this.noticeId = noticeId;
        this.courseId = courseId;
    }

    public NoticeId(Long noticeId, Long courseId) {
        this.noticeId = noticeId;
        this.courseId = new CourseId(courseId);
    }

    public NoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public NoticeId() {
        courseId = new CourseId();
    }

//    public static NoticeId.Builder Builder(){
//        return new Builder();
//    }
//
//    public static class Builder{
//        private Long noticeId;
//        private CourseId courseId;
//        public Builder getNoticeId(Long noticeId){
//            this.noticeId =noticeId;
//            return this;
//        }
//        public Builder setCourseId(Long courseId){
//            this.courseId = new CourseId(courseId);
//            return this;
//        }
//        public NoticeId build(){
//            NoticeId noticeId =new NoticeId();
//            noticeId.courseId=this.courseId;
//            noticeId.noticeId=this.noticeId;
//            return noticeId;
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticeId noticeId1 = (NoticeId) o;
        return Objects.equals(noticeId, noticeId1.noticeId) && Objects.equals(courseId, noticeId1.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noticeId, courseId);
    }
}
