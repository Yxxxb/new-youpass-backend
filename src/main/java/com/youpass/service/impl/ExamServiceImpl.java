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
    public Result<Object> GetExamsByCourseId(Long courseIdGet) {
        if (courseIdGet == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        if (courseRepository.existsById(new CourseId(courseIdGet))) {
            CourseId courseId = new CourseId(courseIdGet);
            var course = courseRepository.findById(courseId).get();
            List<ExamReturnInfo> exams = new ArrayList<>();
            for (Exam e : course.getExamSet()) {
                exams.add(new ExamReturnInfo(e.getId(), e.getStart_time(), e.getEnd_time(), e.getTitle()));
            }
            return ResultUtil.success(exams);
        } else {
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        }
    }

    @Override
    public Result<Object> GetExamByStudentId(Long studentIdGet) {
        StudentId studentId = new StudentId(studentIdGet);
        var student = studentRepository.findById(studentId).get();
        List<ExamReturnInfo> exams = new ArrayList<>();
        for (ExamInfo s : student.getExamInfos()) {
            exams.add(new ExamReturnInfo(
                    s.getExam().getId(),
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
        ExamId examId = new ExamId(releaseExamInfo.getExamId());
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
    public Result<Object> SetSession(HttpServletRequest request, Long studentIdGet, SetSessionInfo setSessionInfo) throws ParseException {
        StudentId studentId = new StudentId(studentIdGet);
        ExamId examId = new ExamId(setSessionInfo.getExamId());
        CourseId courseId = new CourseId(setSessionInfo.getCourseId());

        var student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Did Not Find Student"));
        Exam exam = new Exam();
        ExamInfo examInfo = new ExamInfo();
        for (ExamInfo s : student.getExamInfos()) {
            if (s.getExam().getCourse().getId() == courseId && s.getExam().getId() == examId) {
                exam = s.getExam();
                examInfo = s;
                break;
            }
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("exam_id", setSessionInfo.getExamId());
        session.setAttribute("course_id", setSessionInfo.getCourseId());

        //getstate是null报错
//        if(examInfo.getState()==0 || examInfo.getState()==1){
//            examInfo.setState(1);
//            examInfoRepository.save(examInfo);
//        }
        return ResultUtil.success();

//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        Date startTime = exam.getStart_time();
//        Date endTime = exam.getEnd_time();
//        Date date = new Date();
//        String nowTimeString = formatter.format(new Date());
//        Date nowTime = formatter.parse(nowTimeString);
//
//        if ((nowTime.before(startTime)) && ((nowTime.after(endTime)))) {
//            HttpSession session = request.getSession(true);
//            session.setAttribute("exam_id", setSessionInfo.getExamId());
//            session.setAttribute("course_id", setSessionInfo.getCourseId());
//
//            if(examInfo.getState()==0 || examInfo.getState()==1){
//                examInfo.setState(1);
//                examInfoRepository.save(examInfo);
//            }
//            return ResultUtil.success();
//        } else {
//            return ResultUtil.error(ResultEnum.COURSE_MISS);
//        }
    }

    @Override
    @Transactional
    public Result<Object> PostAnswer(HttpServletRequest request, Long studentIdGet, PostAnswerInfo postAnswerInfo) {
        System.out.println("---------------------------");
        System.out.println(request.getAttribute("exam_id"));
        System.out.println(request.getAttribute("course_id"));

        StudentId studentId = new StudentId(studentIdGet);
        ExamId examId = new ExamId((Long) request.getAttribute("exam_id"));
        CourseId courseId = new CourseId((Long) request.getAttribute("course_id"));
        QuestionId questionId = new QuestionId(postAnswerInfo.getQuestionId());

        var student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Did Not Find Student"));
        ExaminationPaper examinationPaper = new ExaminationPaper();
        Question question = new Question();
        for (ExaminationPaper s : student.getExaminationPaperSet()) {
            if (s.getExam().getCourse().getId() == courseId && s.getExam().getId() == examId && s.getQuestion().getId() == questionId) {
                examinationPaper = s;
                question = s.getQuestion();
                break;
            }
        }

        if (question.getType() == 0 || question.getType() == 1) {
            examinationPaper.setStuAnswer(Tools.RandOrderIndexReturn(
                    examinationPaper.getSelfOrder(),
                    question.getOptionSet().size(),
                    postAnswerInfo.getStuChoiceAnswer()));
            examinationPaperRepository.save(examinationPaper);
        } else if (question.getType() == 2 || question.getType() == 3) {
            examinationPaper.setStuAnswer(postAnswerInfo.getStuFillAnswer());
            examinationPaperRepository.save(examinationPaper);
        } else {
            return ResultUtil.error(ResultEnum.ANSWER_TYPE_MISS);
        }
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result<Object> PostAnswer(Long exam_id,Long course_id, Long studentIdGet, PostAnswerInfo postAnswerInfo) {

        StudentId studentId = new StudentId(studentIdGet);
        CourseId courseId = new CourseId(course_id);
        ExamId examId = new ExamId(exam_id,courseId);

        QuestionId questionId = new QuestionId(postAnswerInfo.getQuestionId());

        var student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Did Not Find Student"));
        ExaminationPaper examinationPaper = new ExaminationPaper();
        Question question = new Question();
        boolean flag = true;
        for (ExaminationPaper s : student.getExaminationPaperSet()) {
            //TODO : 修改
            if (s.getExam().getCourse().getId().getCourseId().equals(courseId.getCourseId())  && s.getExam().getId().getExamId().equals(examId.getExamId()) &&s.getQuestion().getId().getQuestionId().equals(questionId.getQuestionId()) ) {
                examinationPaper = s;
                question = s.getQuestion();
                flag = false;
                break;
            }
        }
        if(flag) {
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }

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

    @Override
    public Result<Object> DeleteSession(HttpServletRequest request, Long studentIdGet) {
        StudentId studentId = new StudentId(studentIdGet);
        ExamId examId = new ExamId((Long) request.getAttribute("exam_id"));
        CourseId courseId = new CourseId((Long) request.getAttribute("course_id"));

        var student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("Did Not Find Student"));
        ExamInfo examInfo = new ExamInfo();
        for (ExamInfo s : student.getExamInfos()) {
            if (s.getExam().getCourse().getId() == courseId && s.getExam().getId() == examId) {
                examInfo = s;
                break;
            }
        }

//        examInfo.setState(2);
//        examInfoRepository.save(examInfo);
        request.getSession().removeAttribute("exam_id");
        request.getSession().removeAttribute("course_id");
        return ResultUtil.success();
    }

    @Override
    public Result<Object> GetExamQuestion(HttpServletRequest request, Long studentIdGet) {
        StudentId studentId = new StudentId(studentIdGet);
        ExamId examId = new ExamId((Long) request.getAttribute("exam_id"));
        CourseId courseId = new CourseId((Long) request.getAttribute("course_id"));

        // 找到exam和examinationPaper
        var student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Did Not Find Student"));
        var exam = examRepository.findById(examId).orElseThrow(() -> new IllegalStateException("Did Not Find Exam"));
        List<ExaminationPaper> examinationPaperList = new ArrayList<>();
        for (ExaminationPaper s : student.getExaminationPaperSet()) {
            if (s.getExam().getCourse().getId() == courseId && s.getExam().getId() == examId) {
                examinationPaperList.add(s);
            }
        }

        examinationPaperList.sort(Comparator.comparing(ExaminationPaper::getNumInPaper));
        List<QuestionInfoReturn> questionList = new ArrayList<>();
        for (ExaminationPaper s : examinationPaperList){
            List<OptionInfo> options = new ArrayList<>();
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
    public Result<Object> GetExamQuestion(Long exam_id,Long course_id, Long studentIdGet) {
        StudentId studentId = new StudentId(studentIdGet);
        CourseId courseId = new CourseId(course_id);
        ExamId examId = new ExamId(exam_id, courseId);


        // 找到exam和examinationPaper
        var student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Did Not Find Student"));
        var exam = examRepository.findById(examId).orElseThrow(() -> new IllegalStateException("Did Not Find Exam"));
        List<ExaminationPaper> examinationPaperList = new ArrayList<>();
        for (ExaminationPaper s : student.getExaminationPaperSet()) {
            if (s.getExam().getCourse().getId() == courseId && s.getExam().getId() == examId) {
                examinationPaperList.add(s);
            }
        }

        examinationPaperList.sort(Comparator.comparing(ExaminationPaper::getNumInPaper));
        List<QuestionInfoReturn> questionList = new ArrayList<>();
        for (ExaminationPaper s : examinationPaperList){
            List<OptionInfo> options = new ArrayList<>();
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
    public Result<Object> CheckState(Long id) {
        //因为有拦截器 所以到了这里就一定可以
        return ResultUtil.success();
    }
}
