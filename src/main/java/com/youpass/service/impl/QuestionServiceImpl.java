package com.youpass.service.impl;

import com.youpass.dao.ExaminationPaperRepository;
import com.youpass.dao.OptionRepository;
import com.youpass.dao.QuestionRepository;
import com.youpass.model.OptionInfo;
import com.youpass.model.QuestionInfo;
import com.youpass.pojo.*;
import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.ExaminationPaperId;
import com.youpass.pojo.pk.OptionId;
import com.youpass.pojo.pk.QuestionId;
import com.youpass.service.QuestionService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final ExaminationPaperRepository examinationPaperRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, OptionRepository optionRepository, ExaminationPaperRepository examinationPaperRepository) {
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
        this.examinationPaperRepository = examinationPaperRepository;
    }


    @Override
    public Result<Object> uploadQuestion(HttpServletRequest request, List<QuestionInfo> questionInfoList) {
        //获取session
        if (request.getSession() == null) {
            return ResultUtil.error(ResultEnum.SESSION_MISS);
        }
        var session = request.getSession();
        Long studentId = (Long) session.getAttribute("id");
        //错误处理
        for (QuestionInfo item : questionInfoList) {
            if (item.getDescription() == null ||
                    item.getCreateTime() == null ||
                    item.getType() == null ||
                    item.getCourseId() == null ||
                    item.getSubject() == null ||
                    item.getStandardAnswer() == null) {
                return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
            }
            //如果上传选择题但是没有上传选项，视为错误
            if ((item.getType() == 0 || item.getType() == 1) && item.getOptionInfoList().size() == 0) {
                return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
            }
            //获取下一个questionId
            var questionId = questionRepository.getNextId().orElse(questionRepository.minId);
            //将题目信息上传到数据库中
            var question = Question.Builder()
                    .setId(new QuestionId(questionId))
                    .setCourse(Course.Builder().setId(new CourseId(item.getCourseId())).build())
                    .setDescription(item.getDescription())
                    .setCreate_time(item.getCreateTime())
                    .setStandard_answer(item.getStandardAnswer())
                    .setSubject(item.getSubject())
                    .setType(item.getType())
                    .build();
            questionRepository.save(question);
            //如果该题是选择题，则将选择题插入option表中
            if (item.getType() == 0 || item.getType() == 1) {
                int count = 0;
                for (OptionInfo optionitem : item.getOptionInfoList()) {
                    if (optionitem.getOptionId() == null || optionitem.getContent() == null) {
                        return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
                    }
                    var option = Option.Builder()
                            .setId(new OptionId(optionitem.getOptionId()))
                            .setQuestion(question)
                            .setContent(optionitem.getContent())
                            .build();
                    optionRepository.save(option);
                    count++;
                }
                if (count != item.getOptionInfoList().size()) {
                    return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
                }
            }
        }
        return ResultUtil.success("上传题目成功");


    }

    @Override
    public Result<Object> getStudentDoingQuestion(QuestionInfo questionInfo) {
        //错误处理
        if (questionInfo.getQuestionId() == null ||
                questionInfo.getCourseId() == null ||
                questionInfo.getExamId() == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        var examinationPaperList = examinationPaperRepository.findById(new ExaminationPaperId(questionInfo.getExamId(), questionInfo.getCourseId(), questionInfo.getQuestionId()));
        return null;

    }
}
