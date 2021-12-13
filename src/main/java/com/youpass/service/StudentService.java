package com.youpass.service;

import com.youpass.util.ReturnType.Result.Result;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    public Result<Object> getStuInfoOfCourse(Long id, Long courseId);

    public Result<Object> deleteStudentFromCourse(Long teacherId, Long studentId, Long courseId);
}
