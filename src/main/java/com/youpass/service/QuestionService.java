package com.youpass.service;

import com.youpass.model.QuestionInfo;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QuestionService {
    public Result<Object> uploadQuestion(List<QuestionInfo> questionInfoList);

    Result<Object> getStudentDoingQuestion(QuestionInfo questionInfo);

    public Result<Object> getUnmarkedQuestion(Long courseId, Long examId);
}
