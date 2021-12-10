package com.youpass.dao;

import com.youpass.pojo.Question;
import com.youpass.pojo.pk.QuestionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, QuestionId> {
}
