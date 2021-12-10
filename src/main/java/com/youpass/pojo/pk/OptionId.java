package com.youpass.pojo.pk;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class OptionId implements Serializable {
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
    private Long optionId;

    @Embedded
    private QuestionId questionId;
}
