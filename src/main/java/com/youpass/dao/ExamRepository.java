package com.youpass.dao;

import com.youpass.pojo.Exam;
import com.youpass.pojo.pk.ExamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, ExamId> {
}
