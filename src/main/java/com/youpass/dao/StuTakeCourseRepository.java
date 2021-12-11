package com.youpass.dao;

import com.youpass.pojo.StuTakeCourse;
import com.youpass.pojo.pk.StuTakeCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StuTakeCourseRepository extends JpaRepository<StuTakeCourse, StuTakeCourseId> {
}
