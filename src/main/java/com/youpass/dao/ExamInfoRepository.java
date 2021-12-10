package com.youpass.dao;

import com.youpass.pojo.ExamInfo;
import com.youpass.pojo.pk.ExamInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamInfoRepository extends JpaRepository<ExamInfo, ExamInfoId> {
}
