package com.youpass.dao;

import com.youpass.pojo.Notice;
import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.NoticeId;
import com.youpass.pojo.pk.QuestionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, NoticeId> {
    public static final Long minId = 1L;

    @Query("SELECT max(n.id.noticeId)+1 FROM Notice n where n.id.courseId = ?1")
    Optional<Long> getNextId(CourseId courseId);
}
