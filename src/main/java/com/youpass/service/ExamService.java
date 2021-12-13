package com.youpass.service;

import com.youpass.model.ReleaseExamInfo;
import com.youpass.model.SetSessionInfo;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Service
public interface ExamService {
    public Result<Object> CheckState(Long id);
    public Result<Object> GetExamsByCourseId(Long courseId);
    public Result<Object> GetExamByStudentId(Long courseId);
    Result<Object> ReleaseTest(Long id, ReleaseExamInfo releaseExamInfo);
    Result<Object> SetSession(HttpServletRequest request, Long id, SetSessionInfo setSessionInfo) throws ParseException;
}
