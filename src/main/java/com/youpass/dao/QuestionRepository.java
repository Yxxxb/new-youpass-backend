package com.youpass.dao;

import com.youpass.pojo.Question;
import com.youpass.pojo.pk.QuestionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, QuestionId> {
    public static final Long minId = 1L;

    @Query("SELECT max(q.id.questionId)+1 FROM Question q")
    Optional<Long> getNextId();
}
