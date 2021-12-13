package com.youpass.service;

import com.youpass.model.QuestionInfo;
import com.youpass.util.ReturnType.Result.Result;

public interface ScoreService {
    Result<Object> calStuScore(QuestionInfo stuInfo);

    Result<Object> getGrade(Long courseId, Long examId);

    Result<Object> autoCorrect(QuestionInfo questionInfo);

    Result<Object> manualCorrect(QuestionInfo questionInfo);

    Result<Object> getStuScore(Long studentId, Long courseId);
}
