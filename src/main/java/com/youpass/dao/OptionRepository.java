package com.youpass.dao;

import com.youpass.pojo.Option;
import com.youpass.pojo.pk.OptionId;
import com.youpass.pojo.pk.QuestionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<Option, OptionId> {
    //TODO:
    public static final Long minId = 0L;

    @Query("SELECT max(q.id.optionId)+1 FROM Option q where q.id.questionId = ?1")
    Optional<Long> getNextId(QuestionId questionId);
}
