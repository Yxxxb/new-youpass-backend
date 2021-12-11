package com.youpass.dao;

import com.youpass.pojo.Student;
import com.youpass.pojo.pk.StudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, StudentId> {
    public static final Long minId = 1950000L;

    @Query("SELECT max(s.id.studentId)+1 FROM Student s")
    Optional<Long> getNextId();

    @Query("SELECT s FROM Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);



}
