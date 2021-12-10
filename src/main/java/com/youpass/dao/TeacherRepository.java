package com.youpass.dao;

import com.youpass.pojo.Teacher;
import com.youpass.pojo.pk.TeacherId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, TeacherId> {
    @Query("SELECT t FROM Teacher t where t.email = ?1")
    Optional<Teacher> findTeacherByEmail(String email);
}
