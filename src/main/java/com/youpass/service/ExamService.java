package com.youpass.service;

import com.youpass.model.CourseInfo;
import com.youpass.model.UserInfo;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.stereotype.Service;

@Service
public interface ExamService {
    public Result<Object> GetExamsByCourseId(Long courseId);
//    public Result<Object> GetExamByStudentId(Long courseId);
}
