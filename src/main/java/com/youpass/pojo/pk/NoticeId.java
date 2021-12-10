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

    public NoticeId() {
    }
}
