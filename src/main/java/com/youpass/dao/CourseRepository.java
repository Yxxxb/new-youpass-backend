package com.youpass.dao;

import com.youpass.pojo.Course;
import com.youpass.pojo.Teacher;
import com.youpass.pojo.pk.CourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, CourseId> {
    public static final Long minId = 1000L;

    @Query(value = "SELECT max(c.id.courseId)+1 FROM Course c")
    Optional<Long> getNextId();

    @Query(value = "SELECT c from Course c where c.title=?1")
    List<Course> findCourseByTitle(String title);
}