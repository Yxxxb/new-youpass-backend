package com.youpass.dao;

import com.youpass.pojo.Course;
import com.youpass.pojo.pk.CourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, CourseId> {
}
