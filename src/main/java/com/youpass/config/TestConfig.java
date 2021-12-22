package com.youpass.config;

import com.youpass.dao.*;
import com.youpass.model.*;
import com.youpass.model.ReturnType.ExamQuestionReturn;
import com.youpass.pojo.*;
import com.youpass.pojo.pk.*;
import com.youpass.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class TestConfig {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ExamService examService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private StudentService studentService;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ExaminationPaperRepository examinationPaperRepository;
    @Autowired
    ExamInfoRepository examInfoRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    OptionRepository optionRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StuTakeCourseRepository stuTakeCourseRepository;
    @Autowired
    TeacherRepository teacherRepository;


    @Bean
    @Transactional
    CommandLineRunner commandLineRunner() {
        return args -> {

//            var userInfo = new UserInfo();
//            userInfo.setName("student" );
//            userInfo.setPassword("123");
//            userInfo.setEmail("123@123");
//            userInfo.setType(1);
//            accountService.SignUp(userInfo);
//
//            var userInfo1 = new UserInfo();
//            userInfo1.setName("teacher" );
//            userInfo1.setPassword("123");
//            userInfo1.setEmail("123@123");
//            userInfo1.setType(0);
//            accountService.SignUp(userInfo1);
//
//
//            CourseInfo courseInfo = new CourseInfo();
//            courseInfo.setTitle("course");
//            courseInfo.setPassword("123");
//            courseService.createCourse(10500L, courseInfo);
//
////
//            var course = courseRepository.findById(new CourseId(1000L)).get();
//            course.setTitle("123lkjfasl; fewr");
//            courseRepository.save(course);
////
//            var student1 = studentRepository.findById(new StudentId(1950000L)).get();
//
//            var stucourse1 = StuTakeCourse.Builder()
////                    .setId(new StuTakeCourseId(s1.getId().getStudentId(),course1.getId().getCourseId()))
//                    .setStudent(student1)
//                    .setCourse(course)
//                    .build();
////            stuTakeCourseRepository.save(stucourse1);
//
//            student1.getStuTakeCourses().add(stucourse1);
//            studentRepository.save(student1);
//            System.out.println(course.getStuTakeCourses().toString());
//            System.out.println(student1.getStuTakeCourses().toString());


            {
                var userInfo = new UserInfo();
                userInfo.setName("student");
                userInfo.setPassword("123");
                userInfo.setEmail("123@123");
                userInfo.setType(1);
                accountService.SignUp(userInfo);

                userInfo.setName("teacher");
                userInfo.setPassword("123");
                userInfo.setEmail("123@123");
                userInfo.setType(0);
                accountService.SignUp(userInfo);

                CourseInfo courseInfo = new CourseInfo();
                courseInfo.setTitle("course");
                courseInfo.setPassword("123");
                courseService.createCourse(10500L, courseInfo);

                courseInfo.setCourseId(1000L);
                courseInfo.setPassword("123");
                courseService.joinCourse(1950000L, courseInfo);

                Integer choiceNum = 5;
                Integer multiNum = 5;
                Integer FilledNum = 5;
                Integer Completion = 5;

                List<QuestionInfo> questionInfoList = new ArrayList<>();
                for (Integer i = 0; i < choiceNum; i++) {
                    QuestionInfo questionInfo = new QuestionInfo();
                    questionInfo.setDescription("des" + i.toString());
                    questionInfo.setCreateTime(new Date());
                    questionInfo.setType(0);
                    questionInfo.setCourseId(1000L);
                    questionInfo.setSubject("123");
                    questionInfo.setStandardAnswer("0");
                    List<OptionInfo> optionList = new ArrayList<>();
                    for (Long j = 1L; j < 4L; j++) {
                        optionList.add(new OptionInfo(j, 0L, "123"));
                    }
                    questionInfo.setOptionInfoList(optionList);
                    questionInfoList.add(questionInfo);
                }
                for (Integer i = 0; i < multiNum; i++) {
                    QuestionInfo questionInfo = new QuestionInfo();
                    questionInfo.setDescription("des" + i.toString());
                    questionInfo.setCreateTime(new Date());
                    questionInfo.setType(1);
                    questionInfo.setCourseId(1000L);
                    questionInfo.setSubject("123");
                    questionInfo.setStandardAnswer("0");
                    List<OptionInfo> optionList = new ArrayList<>();
                    for (Long j = 1L; j < 4L; j++) {
                        optionList.add(new OptionInfo(j, 0L, "123"));
                    }
                    questionInfo.setOptionInfoList(optionList);
                    questionInfoList.add(questionInfo);
                }
                for (Integer i = 0; i < multiNum; i++) {
                    QuestionInfo questionInfo = new QuestionInfo();
                    questionInfo.setDescription("des" + i.toString());
                    questionInfo.setCreateTime(new Date());
                    questionInfo.setType(2);
                    questionInfo.setCourseId(1000L);
                    questionInfo.setSubject("123");
                    questionInfo.setStandardAnswer("0");
                    questionInfoList.add(questionInfo);
                }
                for (Integer i = 0; i < multiNum; i++) {
                    QuestionInfo questionInfo = new QuestionInfo();
                    questionInfo.setDescription("des" + i.toString());
                    questionInfo.setCreateTime(new Date());
                    questionInfo.setType(3);
                    questionInfo.setCourseId(1000L);
                    questionInfo.setSubject("123");
                    questionInfo.setStandardAnswer("0");
                    questionInfoList.add(questionInfo);
                }
                questionService.uploadQuestion(questionInfoList);

                var releaseExamInfo = new ReleaseExamInfo();
                releaseExamInfo.setCourseId(1000L);
                releaseExamInfo.setTitle("exam");
                releaseExamInfo.setChoice_num(2);
                releaseExamInfo.setMulti_choice_num(2);
                releaseExamInfo.setFilled_num(2);
                releaseExamInfo.setCompletion_num(2);
                releaseExamInfo.setChoice_value(2);
                releaseExamInfo.setMulti_choice_value(2);
                releaseExamInfo.setFilled_value(2);
                releaseExamInfo.setCompletion_value(2);
                releaseExamInfo.setStart_time(new Date());
                releaseExamInfo.setEnd_time(new Date());
                examService.ReleaseTest(1000L, releaseExamInfo);
            }


//            //添加学生
//            Integer studentNum = 10;
//            for (Integer i = 0; i < studentNum; i++) {
//                var userInfo = new UserInfo();
//                userInfo.setName("student" + i.toString());
//                userInfo.setPassword("123");
//                userInfo.setEmail("123@123" + i.toString());
//                userInfo.setType(1);
//                accountService.SignUp(userInfo);
//            }
//            //添加老师
//            Integer teacherNum = 10;
//            for (Integer i = 0; i < teacherNum; i++) {
//                var userInfo = new UserInfo();
//                userInfo.setName("teacher" + i.toString());
//                userInfo.setPassword("123");
//                userInfo.setEmail("123@123" + i.toString());
//                userInfo.setType(0);
//                accountService.SignUp(userInfo);
//            }
//            //为老师添加课程
//            Integer courseNum = 2;
//            var teacherList = teacherRepository.findAll();
//            for (var teacher : teacherList) {
//                for (Integer i = 0; i < courseNum; i++) {
//                    CourseInfo courseInfo = new CourseInfo();
//                    courseInfo.setTitle("course" + i.toString());
//                    courseInfo.setPassword("123");
//                    courseService.createCourse(teacher.getId().getTeacherId(), courseInfo);
//                }
//            }
//
//
//            {
//                //学生加入课程
//                var courseList = courseRepository.findAll();
//                var studentList = studentRepository.findAll();
//                int a = 0;
//                for (var course : courseList) {
//                    for (var student : studentList) {
//                        CourseInfo courseInfo = new CourseInfo();
//                        courseInfo.setCourseId(course.getId().getCourseId());
//                        courseInfo.setPassword(course.getPassword());
//                        courseService.joinCourse(student.getId().getStudentId(), courseInfo);
//                        System.out.println(a);
//                    }
//                    a++;
//                }
//            }
//            //添加题目
//            {
//                Integer choiceNum = 5;
//                Integer multiNum = 5;
//                Integer FilledNum = 5;
//                Integer Completion = 5;
//
//                List<QuestionInfo> questionInfoList = new ArrayList<>();
//                for (Integer i = 0; i < choiceNum; i++) {
//                    QuestionInfo questionInfo = new QuestionInfo();
//                    questionInfo.setDescription("des" + i.toString());
//                    questionInfo.setCreateTime(new Date());
//                    questionInfo.setType(0);
//                    questionInfo.setCourseId(1000L);
//                    questionInfo.setSubject("123");
//                    questionInfo.setStandardAnswer("0");
//                    List<OptionInfo> optionList = new ArrayList<>();
//                    for (Long j = 1L; j < 4L; j++) {
//                        optionList.add(new OptionInfo(j, 0L, "123"));
//                    }
//                    questionInfo.setOptionInfoList(optionList);
//                    questionInfoList.add(questionInfo);
//                }
//                for (Integer i = 0; i < multiNum; i++) {
//                    QuestionInfo questionInfo = new QuestionInfo();
//                    questionInfo.setDescription("des" + i.toString());
//                    questionInfo.setCreateTime(new Date());
//                    questionInfo.setType(1);
//                    questionInfo.setCourseId(1000L);
//                    questionInfo.setSubject("123");
//                    questionInfo.setStandardAnswer("0");
//                    List<OptionInfo> optionList = new ArrayList<>();
//                    for (Long j = 1L; j < 4L; j++) {
//                        optionList.add(new OptionInfo(j, 0L, "123"));
//                    }
//                    questionInfo.setOptionInfoList(optionList);
//                    questionInfoList.add(questionInfo);
//                }
//                for (Integer i = 0; i < multiNum; i++) {
//                    QuestionInfo questionInfo = new QuestionInfo();
//                    questionInfo.setDescription("des" + i.toString());
//                    questionInfo.setCreateTime(new Date());
//                    questionInfo.setType(2);
//                    questionInfo.setCourseId(1000L);
//                    questionInfo.setSubject("123");
//                    questionInfo.setStandardAnswer("0");
//                    questionInfoList.add(questionInfo);
//                }
//                for (Integer i = 0; i < multiNum; i++) {
//                    QuestionInfo questionInfo = new QuestionInfo();
//                    questionInfo.setDescription("des" + i.toString());
//                    questionInfo.setCreateTime(new Date());
//                    questionInfo.setType(3);
//                    questionInfo.setCourseId(1000L);
//                    questionInfo.setSubject("123");
//                    questionInfo.setStandardAnswer("0");
//                    questionInfoList.add(questionInfo);
//                }
//                questionService.uploadQuestion(questionInfoList);
//
//            }

////            考试
//            {
//                var courseList = courseRepository.findAll();
//                var releaseExamInfo = new ReleaseExamInfo();
//                releaseExamInfo.setCourseId(1000L);
//                releaseExamInfo.setTitle("exam");
//                releaseExamInfo.setChoice_num(2);
//                releaseExamInfo.setMulti_choice_num(2);
//                releaseExamInfo.setFilled_num(2);
//                releaseExamInfo.setCompletion_num(2);
//                releaseExamInfo.setChoice_value(2);
//                releaseExamInfo.setMulti_choice_value(2);
//                releaseExamInfo.setFilled_value(2);
//                releaseExamInfo.setCompletion_value(2);
//                examService.ReleaseTest(1000L, releaseExamInfo);
//            }
//
//            //上传答案
//            {
//                var studentList = studentRepository.findAll();
//                for (var student : studentList) {
//                    for (Long i = 1L; i <= 20; i++) {
//                        PostAnswerInfo postAnswerInfo = new PostAnswerInfo();
//                        postAnswerInfo.setQuestionId(i);
//                        postAnswerInfo.setStuFillAnswer("0");
//                        List<Character> stuChoiceAnswer = new ArrayList<Character>();
//                        stuChoiceAnswer.add('C');
//                        postAnswerInfo.setStuChoiceAnswer(stuChoiceAnswer);
//                        System.out.println("@@@");
//                        examService.PostAnswer(1L, 1000L, student.getId().getStudentId(), postAnswerInfo);
//                    }
//
//                }
//            }
//
//            //批卷
//            {
//                //自动批改
//
////                for(Long i =1L;i<=20;i++){
////                    QuestionInfo questionInfo = new QuestionInfo();
////                    questionInfo.setExamId(1L);
////                    questionInfo.setQuestionId(i);
////                    questionInfo.setCourseId(1000L);
////                    scoreService.autoCorrect(questionInfo);
////                }
//
//                var studentList = studentRepository.findAll();
//                //手动批改
//                for(Long i=1L;i<20;i++){
//                    List<ScoreInfo> scoreInfoList = new ArrayList<>();
//                    for(var student : studentList){
//                        ScoreInfo scoreInfo = new ScoreInfo();
//                        scoreInfo.setStudentId(student.getId().getStudentId());
//                        scoreInfo.setScore((int)(Math.random()*100));
//                        scoreInfoList.add(scoreInfo);
//                    }
//                    QuestionInfo questionInfo = new QuestionInfo();
//                    questionInfo.setExamId(1L);
//                    questionInfo.setQuestionId(i);
//                    questionInfo.setCourseId(1000L);
//                    questionInfo.setScoreInfoList(scoreInfoList);
//                    System.out.println("***");
//                    scoreService.manualCorrect(questionInfo);
//                }
//            }


        };
    }


}
