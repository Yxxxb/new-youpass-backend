package com.youpass.dao;

import com.youpass.pojo.Notice;
import com.youpass.pojo.pk.NoticeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, NoticeId> {
}
