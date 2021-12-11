package com.youpass.dao;

import com.youpass.pojo.Course;
import com.youpass.pojo.Teacher;
import com.youpass.pojo.pk.CourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, CourseId> {
    //select max(exam_id)+1 from (select exam_id from exam where course_id=:course_id)
    @Query(value = "SELECT max(course_id)+1 FROM Course", nativeQuery = true)
    Optional<Long> getNextId();
}
