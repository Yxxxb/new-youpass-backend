package com.youpass.pojo.pk;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
public class ExamInfoId implements Serializable {
    @Embedded
    private StudentId studentId;
    @Embedded
    private ExamId examId;

    public ExamInfoId() {
    }
}
