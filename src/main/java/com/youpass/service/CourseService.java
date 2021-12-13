package com.youpass.service;

import com.youpass.util.ReturnType.Result.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestAttribute;

@Service
public interface CourseService {
    public Result<Object> deleteCourse(Long teacherId, Long courseId);

    public Result<Object> getCourseById(Long courseId);

    public Result<Object> getCourseByTitle(String title);

    public Result<Object> getCourseByTName(String teacherName);

    public Result<Object> getCourseOfUser(Long id);
}
