package com.youpass.service;

import com.youpass.model.QuestionInfo;
import com.youpass.util.ReturnType.Result.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QuestionService {
    public Result<Object> uploadQuestion(List<QuestionInfo> questionInfoList);

    Result<Object> getStudentDoingQuestion(QuestionInfo questionInfo);
}
