package com.youpass.dao;

import com.youpass.pojo.ExaminationPaper;
import com.youpass.pojo.pk.ExaminationPaperId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationPaperRepository extends JpaRepository<ExaminationPaper, ExaminationPaperId> {
}
