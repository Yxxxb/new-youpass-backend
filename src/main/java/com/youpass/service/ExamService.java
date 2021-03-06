package com.youpass.service;

import com.youpass.model.PostAnswerInfo;
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

    public Result<Object> ReleaseTest(Long id, ReleaseExamInfo releaseExamInfo);

    public Result<Object> SetSession(HttpServletRequest request, Long id, SetSessionInfo setSessionInfo);

    public Result<Object> PostAnswer(PostAnswerInfo postAnswerInfo);

    public Result<Object> DeleteSession(HttpServletRequest request,Long id,Long courseId,Long examId);

    public Result<Object> GetExamQuestion(Long id,Long courseId,Long examId);
}
