package com.youpass.service.impl;

import com.youpass.dao.*;
import com.youpass.model.QuestionInfo;
import com.youpass.model.ReturnType.CourseExamInfoReturn;
import com.youpass.model.ScoreInfo;
import com.youpass.pojo.*;
import com.youpass.pojo.pk.*;
import com.youpass.service.ScoreService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class ScoreServiceImpl implements ScoreService {
    private final ExamRepository examRepository;
    private final ExaminationPaperRepository examinationPaperRepository;
    private final QuestionRepository questionRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public ScoreServiceImpl(ExamRepository examRepository, ExaminationPaperRepository examinationPaperRepository, QuestionRepository questionRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.examRepository = examRepository;
        this.examinationPaperRepository = examinationPaperRepository;
        this.questionRepository = questionRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public Result<Object> calStuScore(QuestionInfo stuInfo) {
        //错误检测
        if (stuInfo.getCourseId() == null ||
                stuInfo.getExamId() == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        //找到这门课这场考试的所有学生
        Set<Student> studentSet = new HashSet<>();
        Optional<Exam> optionalExam = examRepository.findById(new ExamId(stuInfo.getExamId(), stuInfo.getCourseId()));
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

    @Override
    @Transactional
    public Result<Object> getGrade(Long courseId, Long examId) {
        //错误处理
        if (courseId == null || examId == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        Optional<Exam> examOptional = examRepository.findById(new ExamId(examId, courseId));
        if (examOptional.isEmpty()) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        Set<ExamInfo> examInfoSet = examOptional.get().getExamInfoSet();
        Set<ScoreInfo> scoreInfoSet = new HashSet<>();
        for (ExamInfo examInfo : examInfoSet) {
            scoreInfoSet.add(new ScoreInfo(examInfo.getId().getStudentId(), examInfo.getStudent().getName(), examInfo.getScore()));
        }
        return ResultUtil.success(scoreInfoSet);

    }

    @Override
    @Transactional
    public Result<Object> autoCorrect(QuestionInfo questionInfo) {
        //错误处理
        if (questionInfo.getExamId() == null ||
                questionInfo.getQuestionId() == null ||
                questionInfo.getCourseId() == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        Optional<Question> questionOptional = questionRepository.findById(new QuestionId(questionInfo.getQuestionId()));
        if (questionOptional.isEmpty()) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        //将正确答案先存起来
        String standard_answer = questionOptional.get().getStandard_answer();

        Optional<Exam> examOptional = examRepository.findById(new ExamId(questionInfo.getExamId(), questionInfo.getCourseId()));
        if (examOptional.isEmpty()) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }

        Set<Student> studentSet = new HashSet<>();
        Set<ExaminationPaper> examinationPaperSet = examOptional.get().getExaminationPaperSet();

        //下面的Set是除了studentId外剩下的主码都确定的Set，也就是我们需要的Set
        Set<ExaminationPaper> examinationPaperSetInNeed = new HashSet<>();

        for (ExaminationPaper examinationPaper : examinationPaperSet) {
            if (examinationPaper.getQuestion().getId().getQuestionId().equals(questionInfo.getQuestionId())) {
                examinationPaperSetInNeed.add(examinationPaper);
            }
        }

        //遍历：比较分数然后给分,并存入数据库
        for (ExaminationPaper examinationPaper : examinationPaperSetInNeed) {
            if (standard_answer.equals(examinationPaper.getStuAnswer())) {
                examinationPaper.setStuPoint(examinationPaper.getValue());
            } else {
                examinationPaper.setStuPoint(0);
            }
            examinationPaperRepository.save(examinationPaper);
        }

        return ResultUtil.success("该题自动批改完成");
    }

    @Override
    @Transactional
    public Result<Object> manualCorrect(QuestionInfo questionInfo) {
        //错误处理
        if (questionInfo.getExamId() == null ||
                questionInfo.getQuestionId() == null ||
                questionInfo.getCourseId() == null ||
                questionInfo.getScoreInfoList() == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        for(ScoreInfo scoreInfo:questionInfo.getScoreInfoList()){
            Optional<ExaminationPaper> examinationPaperOptional = examinationPaperRepository.findById(new ExaminationPaperId(
                    scoreInfo.getStudentId(),
                    questionInfo.getExamId(),
                    questionInfo.getCourseId(),
                    questionInfo.getQuestionId()));
            if(examinationPaperOptional.isEmpty()){
                return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
            }
            examinationPaperOptional.get().setStuPoint(scoreInfo.getScore());
            examinationPaperRepository.save(examinationPaperOptional.get());


        }
        System.out.println("3123123");

        return ResultUtil.success("该题手动批改完成");
    }

    @Override
    @Transactional
    public Result<Object> getStuScore(Long studentId, Long courseId) {
        if(studentId==null||courseId==null){
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        Optional<Course> courseOptional = courseRepository.findById(new CourseId(courseId));
        if(courseOptional.isEmpty()){
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        //最终要返回的信息
        Set<CourseExamInfoReturn> courseExamInfoReturnSet=new HashSet<>();

        Set<Exam> examSet =courseOptional.get().getExamSet();
        for( Exam exam :examSet){
            Set<ExamInfo> examInfoSet = exam.getExamInfoSet();
            for(ExamInfo examInfo :examInfoSet){
                if (examInfo.getId().getStudentId().equals(studentId)){
                    courseExamInfoReturnSet.add(new CourseExamInfoReturn(
                            courseId,
                            exam.getId().getExamId(),
                            exam.getTitle(),
                            examInfo.getScore()
                    ));
                    break;
                }
            }
        }
        return ResultUtil.success(courseExamInfoReturnSet);
    }
}
