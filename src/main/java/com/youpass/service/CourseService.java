package com.youpass.service;

import com.youpass.model.CourseInfo;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    public Result<Object> deleteCourse(Long teacherId, Long courseId);

    Result<Object> createCourse(Long teacherId, CourseInfo courseInfo);

    Result<Object> joinCourse(Long studentId, CourseInfo courseInfo);
}
