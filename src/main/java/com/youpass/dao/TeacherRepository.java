package com.youpass.dao;

import com.youpass.pojo.Teacher;
import com.youpass.pojo.pk.TeacherId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, TeacherId> {
    public static final Long minId = 10500L;

    @Query("SELECT max(t.id.teacherId)+1 FROM Teacher t")
    Optional<Long> getNextId();

    @Query("SELECT t FROM Teacher t where t.email = ?1")
    Optional<Teacher> findTeacherByEmail(String email);

    @Query("SELECT t from Teacher t where t.name=?1")
    List<Teacher> findTeacherByName(String name);
}
