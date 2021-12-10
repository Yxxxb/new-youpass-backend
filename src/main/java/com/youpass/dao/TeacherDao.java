package com.youpass.dao;

import com.youpass.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDao extends JpaRepository<Teacher, Long> {
}
