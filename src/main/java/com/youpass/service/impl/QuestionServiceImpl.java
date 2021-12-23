package com.youpass.service.impl;

import com.youpass.dao.ExamRepository;
import com.youpass.dao.ExaminationPaperRepository;
import com.youpass.dao.OptionRepository;
import com.youpass.dao.QuestionRepository;
import com.youpass.model.OptionInfo;
import com.youpass.model.QuestionInfo;
import com.youpass.model.ReturnType.QuestionStuReturn;
import com.youpass.model.StudentExamPaperInfo;
import com.youpass.model.UnmarkedQuestionInfo;
import com.youpass.pojo.*;
import com.youpass.pojo.pk.*;
import com.youpass.service.QuestionService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final ExamRepository examRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, OptionRepository optionRepository, ExamRepository examRepository) {
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
        this.examRepository = examRepository;
    }


    @Override
    @Transactional
    public Result<Object> uploadQuestion(List<QuestionInfo> questionInfoList) {

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
            if ((item.getType() == 0 || item.getType() == 1) && (item.getOptionInfoList() == null || item.getOptionInfoList().size() == 0)) {
                return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
            }
            //TODO:将传来的standard转换编码存入
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
    @Transactional
    public Result<Object> getStudentDoingQuestion(QuestionInfo questionInfo) {
        //错误处理
        if (questionInfo.getQuestionId() == null ||
                questionInfo.getCourseId() == null ||
                questionInfo.getExamId() == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }

        var exam = examRepository.findById(new ExamId(questionInfo.getExamId(), questionInfo.getCourseId()));
        if (exam.isEmpty()) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        var epSet = exam.get().getExaminationPaperSet();
        //最终要返回的学生列表
        Set<StudentExamPaperInfo> studentReturnSet = new HashSet<>();

        //将满足要求的学生加入Set中
        for (var item : epSet) {
            if (item.getQuestion().getId().getQuestionId().equals(questionInfo.getQuestionId()) && (item.getStuPoint() == null)) {
                studentReturnSet.add(new StudentExamPaperInfo(
                        item.getId().getStudentId(),
                        item.getNumInPaper(),
                        item.getStuAnswer(),
                        item.getValue()));
            }
        }

        //找到相应的题目
        Optional<Question> questionOptional = questionRepository.findById(new QuestionId(questionInfo.getQuestionId()));
        if (questionOptional.isEmpty()) {
            return ResultUtil.error(ResultEnum.USER_MISS);
        }
        //题目信息
        Question question = questionOptional.get();

        //最终要返回选项的List
        List<OptionInfo> optionInfoList = new ArrayList<>();

        //将选项添加到List中
        for (var item : question.getOptionSet()) {
            optionInfoList.add(new OptionInfo(
                    item.getId().getOptionId(),
                    item.getId().getQuestionId().getQuestionId(),
                    item.getContent()));
        }
        //最终要返回的题目信息
        QuestionInfo questionInfoReturn = new QuestionInfo(
                question.getId().getQuestionId(),
                question.getDescription(),
                question.getType(),
                question.getStandard_answer(),
                question.getSubject(),
                optionInfoList);

        return ResultUtil.success(new QuestionStuReturn(studentReturnSet, questionInfoReturn));

    }

    @Override
    public Result<Object> getUnmarkedQuestion(Long courseId, Long examId) {
        var res = examRepository.findById(new ExamId(examId,courseId));
        if(res.isPresent()==false){
            return ResultUtil.error(ResultEnum.EXAM_MISS);
        }
        List<UnmarkedQuestionInfo> infoList = new ArrayList<>();
        Map<Long,Integer> unmarkedQuestionNumber = new HashMap<>();
        var examT = res.get();
        for(var paper:examT.getExaminationPaperSet()){
            if(paper.getStuPoint()==null){
                var questionId = paper.getQuestion().getId().getQuestionId();
                if(unmarkedQuestionNumber.get(questionId)==null){
                    unmarkedQuestionNumber.put(questionId,1);
                }else{
                    unmarkedQuestionNumber.put(questionId,unmarkedQuestionNumber.get(questionId)+1);
                }
            }
        }
        if(unmarkedQuestionNumber.size()>0){
            for(var entry:unmarkedQuestionNumber.entrySet()){
                UnmarkedQuestionInfo unmarkedQuestionInfo = new UnmarkedQuestionInfo();
                unmarkedQuestionInfo.setQuestionId(entry.getKey());
                unmarkedQuestionInfo.setRestNumber(entry.getValue());
                var question = questionRepository.findById(new QuestionId(entry.getKey())).get();
                unmarkedQuestionInfo.setDescription(question.getDescription());
                infoList.add(unmarkedQuestionInfo);
            }

            return ResultUtil.success(infoList);
        }else{
            //已经完成了批改
            //找到这门课这场考试的所有学生
            Set<Student> studentSet = new HashSet<>();
            Optional<Exam> optionalExam = examRepository.findById(new ExamId(examId, courseId));
            if (optionalExam.isEmpty()) {
                return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
            }
            Exam exam = optionalExam.get();
            Set<ExamInfo> examInfoSet = exam.getExamInfoSet();
            Set<ExaminationPaper> examinationPaperSet = exam.getExaminationPaperSet();
            for (ExamInfo item : examInfoSet) {
                studentSet.add(item.getStudent());
            }
            //对该场考试的每一个学生，计算其总分数
            for (Student student : studentSet) {
                Integer sumScore = 0;
                for (ExaminationPaper examinationPaper : examinationPaperSet) {
                    if (examinationPaper.getId().getStudentId().equals(student.getId().getStudentId())) {
                        sumScore += examinationPaper.getStuPoint();
                    }
                }
                for (ExamInfo examInfo : examInfoSet) {
                    if (examInfo.getStudent().getId().getStudentId().equals(student.getId().getStudentId())) {
                        examInfo.setScore(sumScore);
                        examRepository.save(exam);
                        break;
                    }
                }
            }
            return ResultUtil.success("该门考试成绩录入成功！");
        }

    }
}
