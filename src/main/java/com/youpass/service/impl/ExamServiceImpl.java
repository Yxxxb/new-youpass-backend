package com.youpass.service.impl;

import com.youpass.dao.*;
import com.youpass.model.*;
import com.youpass.model.ReturnType.ExamQuestionReturn;
import com.youpass.model.ReturnType.QuestionInfoReturn;
import com.youpass.pojo.*;
import com.youpass.pojo.pk.*;
import com.youpass.service.ExamService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import com.youpass.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ExamInfoRepository examInfoRepository;
    private final ExaminationPaperRepository examinationPaperRepository;
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public ExamServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, ExamInfoRepository examInfoRepository, ExaminationPaperRepository examinationPaperRepository, ExamRepository examRepository, QuestionRepository questionRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.examInfoRepository = examInfoRepository;
        this.examinationPaperRepository = examinationPaperRepository;
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    @Transactional
    public Result<Object> GetExamsByCourseId(Long courseIdGet) {
        if (courseIdGet == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        if (courseRepository.existsById(new CourseId(courseIdGet))) {
            CourseId courseId = new CourseId(courseIdGet);
            var course = courseRepository.findById(courseId).get();
            List<ExamReturnInfo> exams = new ArrayList<>();
            for (Exam e : course.getExamSet()) {
                exams.add(new ExamReturnInfo(e.getId().getCourseId(), e.getId().getExamId(), e.getStart_time(), e.getEnd_time(), e.getTitle()));
            }
            return ResultUtil.success(exams);
        } else {
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        }
    }

    @Override
    @Transactional
    public Result<Object> GetExamByStudentId(Long studentIdGet) {
        StudentId studentId = new StudentId(studentIdGet);
        var student = studentRepository.findById(studentId).get();
        List<ExamReturnInfo> exams = new ArrayList<>();
        for (ExamInfo s : student.getExamInfos()) {
            exams.add(new ExamReturnInfo(
                    s.getExam().getId().getCourseId(),
                    s.getExam().getId().getExamId(),
                    s.getExam().getStart_time(),
                    s.getExam().getEnd_time(),
                    s.getExam().getTitle()));
        }
        return ResultUtil.success(exams);
    }

    @Override
    @Transactional
    public Result<Object> ReleaseTest(Long teacherIdGet, ReleaseExamInfo releaseExamInfo) {

        TeacherId teacherId = new TeacherId(teacherIdGet);
        CourseId courseId = new CourseId(releaseExamInfo.getCourseId());

        Course course = new Course();
        if (courseRepository.findById(courseId).isPresent()) {
            course = courseRepository.findById(courseId).get();
        } else {
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        }


        // 判断老师和课程匹配

        // 判断学生有无

        // 判断考试名重复


        var exam = Exam.Builder()
                .setId(new ExamId(examRepository.getNextId(courseId).isPresent() ?
                        examRepository.getNextId(courseId).get() : examRepository.minId))
                .setTitle(releaseExamInfo.getTitle())
                .setChoiceNum(releaseExamInfo.getChoice_num())
                .setMultiChoiceNum(releaseExamInfo.getMulti_choice_num())
                .setFilledNum(releaseExamInfo.getFilled_num())
                .setCompletionNum(releaseExamInfo.getCompletion_num())
                .setStart_time(releaseExamInfo.getStart_time())
                .setEnd_time((releaseExamInfo.getEnd_time()))
                .setCourse(course)
                .build();

        course.getExamSet().add(exam);
        courseRepository.save(course);


        for (StuTakeCourse stuTakeCourse : course.getStuTakeCourses()) {
            var examInfo = ExamInfo.Builder()
                    .setId(new ExamInfoId(exam.getId().getExamId(), exam.getId().getCourseId(),
                            stuTakeCourse.getStudent().getId().getStudentId()))
                    .setScore(null)
                    .setState(1)
                    .setExam(exam)
                    .setStudent(stuTakeCourse.getStudent())
                    .build();
            exam.getExamInfoSet().add(examInfo);
            stuTakeCourse.getStudent().getExamInfos().add(examInfo);
            studentRepository.save(stuTakeCourse.getStudent());
            examRepository.save(exam);
        }

        List<Integer> numberList = new ArrayList<>(Arrays.asList(
                releaseExamInfo.getChoice_num(),
                releaseExamInfo.getMulti_choice_num(),
                releaseExamInfo.getFilled_num(),
                releaseExamInfo.getCompletion_num()));

        List<Integer> valueList = new ArrayList<>(Arrays.asList(
                releaseExamInfo.getChoice_value(),
                releaseExamInfo.getMulti_choice_value(),
                releaseExamInfo.getFilled_value(),
                releaseExamInfo.getCompletion_value()));

        int numInPaperer = 0;

        for (int i = 0; i < numberList.size(); i++) {
            List<Question> questionList = new ArrayList<>();
            for (Question q : course.getQuestionSet()) {
                if (q.getType() == i) {
                    questionList.add(q);
                }
            }
            if (numberList.get(i) > questionList.size()) {
                return ResultUtil.error(ResultEnum.QUESTION_ERROR);
            } else {
                List<Question> pickedQuestion = questionList.subList(0, numberList.get(i));
                for (Question pq : pickedQuestion) {
                    numInPaperer += 1;
                    for (StuTakeCourse stuTakeCourse : course.getStuTakeCourses()) {
                        int selfOrder = i < 2 ? (int) (Math.random() * 6 + 1) : -1;
                        var ep = ExaminationPaper.Builder()
                                .setId(new ExaminationPaperId(stuTakeCourse.getStudent().getId().getStudentId(),
                                        exam.getId().getExamId(), exam.getId().getCourseId(), pq.getId().getQuestionId()))
                                .setQuestion(pq)
                                .setExam(exam)
                                .setStudent(stuTakeCourse.getStudent())
                                .setNumInPaper(numInPaperer)
                                .setSelfOrder(selfOrder)
                                .setValue(valueList.get(i))
                                .build();

                        stuTakeCourse.getStudent().getExaminationPaperSet().add(ep);
                        exam.getExaminationPaperSet().add(ep);
                        pq.getExaminationPaperSet().add(ep);
                        studentRepository.save(stuTakeCourse.getStudent());
                        questionRepository.save(pq);
                        examRepository.save(exam);
                    }
                }
            }
        }

        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result<Object> SetSession(HttpServletRequest request, Long studentIdGet, SetSessionInfo setSessionInfo) {
        if (setSessionInfo.getExamId() == null
                || setSessionInfo.getCourseId() == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }

        if (!studentRepository.existsById(new StudentId(studentIdGet))) {
            return ResultUtil.error(ResultEnum.USER_MISS);
        }
        var student = studentRepository.findById(new StudentId(studentIdGet)).get();
        for (ExamInfo s : student.getExamInfos()) {
            if (s.getExam().getId().getCourseId().equals(setSessionInfo.getCourseId())
                    && s.getExam().getId().getExamId().equals(setSessionInfo.getExamId())) {
                HttpSession session = request.getSession(true);
                session.setAttribute("examId", setSessionInfo.getExamId());
                session.setAttribute("courseId", setSessionInfo.getCourseId());
                return ResultUtil.success();
            }
        }
        //未找到对应考试
        return ResultUtil.error(ResultEnum.EXAM_MISS);
    }

    @Override
    @Transactional
    public Result<Object> PostAnswer(PostAnswerInfo postAnswerInfo) {
        if (postAnswerInfo.getStudentId() == null
                || postAnswerInfo.getExamId() == null
                || postAnswerInfo.getCourseId() == null
                || postAnswerInfo.getQuestionId() == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }

        if (!studentRepository.existsById(new StudentId(postAnswerInfo.getStudentId()))) {
            return ResultUtil.error(ResultEnum.USER_MISS);
        }
        var student = studentRepository.findById(new StudentId(postAnswerInfo.getStudentId())).get();
        for (ExaminationPaper s : student.getExaminationPaperSet()) {
            //TODO : 修改
            if (s.getExam().getId().getCourseId().equals(postAnswerInfo.getCourseId())
                    && s.getExam().getId().getExamId().equals(postAnswerInfo.getExamId())
                    && s.getQuestion().getId().getQuestionId().equals(postAnswerInfo.getQuestionId())) {
                //找到了对应的题目
                var examinationPaper = s;
                var question = s.getQuestion();
                if (question.getType() == 0 || question.getType() == 1) {
                    examinationPaper.setStuAnswer(Tools.RandOrderIndexReturn(
                            examinationPaper.getSelfOrder(),
                            question.getOptionSet().size(),
                            postAnswerInfo.getStuChoiceAnswer()));
                    //TODO: 改了
                    studentRepository.save(student);
                    questionRepository.save(question);
                    examRepository.save(examinationPaper.getExam());
                } else if (question.getType() == 2 || question.getType() == 3) {
                    examinationPaper.setStuAnswer(postAnswerInfo.getStuFillAnswer());
                    //TODO: 改了
                    studentRepository.save(student);
                    questionRepository.save(question);
                    examRepository.save(examinationPaper.getExam());
                } else {
                    return ResultUtil.error(ResultEnum.ANSWER_TYPE_MISS);
                }
                return ResultUtil.success();
            }
        }
        return ResultUtil.error(ResultEnum.Question_MISS);
    }

    @Override
    @Transactional
    public Result<Object> DeleteSession(HttpServletRequest request, Long studentId, Long courseId, Long examId) {
        if (!studentRepository.existsById(new StudentId(studentId))) {
            return ResultUtil.error(ResultEnum.NOT_STUDENT);
        }
        var student = studentRepository.findById(new StudentId(studentId)).get();
        for (ExamInfo s : student.getExamInfos()) {
            if (s.getExam().getId().getExamId().equals(examId)
                    && s.getExam().getId().getCourseId().equals(courseId)) {
                s.setState(2);
                examInfoRepository.save(s);
                request.getSession().removeAttribute("courseId");
                request.getSession().removeAttribute("examId");
                return ResultUtil.success();
            }
        }
        return ResultUtil.error(ResultEnum.EXAM_MISS);
    }

    @Override
    @Transactional
    public Result<Object> GetExamQuestion(Long studentId, Long courseId, Long examId) {
//        StudentId studentId = new StudentId(studentIdGet);
//        CourseId courseId = new CourseId((Long) request.getAttribute("course_id"));
//        ExamId examId = new ExamId((Long) request.getAttribute("exam_id"), courseId);

        // 找到exam和examinationPaper
        var studentRes = studentRepository.findById(new StudentId(studentId));
        if (studentRes.isPresent() == false) {
            return ResultUtil.error(ResultEnum.NOT_STUDENT);
        }
        var examRes = examRepository.findById(new ExamId(examId, courseId));
        if (examRes.isPresent() == false) {
            return ResultUtil.error(ResultEnum.EXAM_MISS);
        }
        var student = studentRes.get();
        var exam = examRes.get();
        List<ExaminationPaper> examinationPaperList = new ArrayList<>();
        for (ExaminationPaper s : student.getExaminationPaperSet()) {
            if (s.getExam().getId().getCourseId().equals(courseId)
                    && s.getExam().getId().getExamId().equals(examId)) {
                examinationPaperList.add(s);
            }
        }

        examinationPaperList.sort(Comparator.comparing(ExaminationPaper::getNumInPaper));
        List<QuestionInfoReturn> questionList = new ArrayList<>();
        for (ExaminationPaper s : examinationPaperList) {
            List<OptionInfo> options = new ArrayList<>();
            if (s.getQuestion().getType() == null) {
                return ResultUtil.error(ResultEnum.ANSWER_TYPE_MISS);
            }
            if (s.getQuestion().getType() < 2) {
                for (Option option : s.getQuestion().getOptionSet()) {
                    options.add(new OptionInfo(
                            option.getId().getOptionId(),
                            s.getQuestion().getId().getQuestionId(),
                            option.getContent()));
                }
                // 加密
                options = Tools.rand_order(s.getSelfOrder(), options);
            }

            questionList.add(new QuestionInfoReturn(
                    s.getQuestion().getId().getQuestionId(),
                    s.getQuestion().getDescription(),
                    s.getQuestion().getType(),
                    s.getNumInPaper(),
                    options));
        }

        ExamQuestionReturn examQuestionReturn = new ExamQuestionReturn(exam.getTitle(), exam.getStart_time(), exam.getEnd_time(), questionList);

        return ResultUtil.success(examQuestionReturn);
    }

    @Override
    @Transactional
    public Result<Object> CheckState(Long id) {
        //因为有拦截器 所以到了这里就一定可以
        return ResultUtil.success();
    }
}
