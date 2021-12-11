package com.youpass.dao;

import com.youpass.pojo.Exam;
import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.ExamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, ExamId> {
    public static Long minId = 1L;

    @Query("SELECT max(e.id.examId)+1 FROM Exam e where e.id.courseId = ?1  ")
    Optional<Long> getNextId(CourseId courseId);
}
