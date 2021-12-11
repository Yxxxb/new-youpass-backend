package com.youpass.config;

import com.youpass.dao.*;
import com.youpass.pojo.*;
import com.youpass.pojo.pk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Configuration
public class TestConfig {
    @Autowired
    public CourseRepository courseRepository;

    @Autowired
    public ExaminationPaperRepository examinationPaperRepository;

    @Autowired
    public ExamInfoRepository examInfoRepository;

    @Autowired
    public ExamRepository examRepository;

    @Autowired
    public NoticeRepository noticeRepository;

    @Autowired
    public OptionRepository optionRepository;

    @Autowired
    public QuestionRepository questionRepository;

    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    public TeacherRepository teacherRepository;

    @Autowired
    public StuTakeCourseRepository stuTakeCourseRepository;

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner() {
        return args -> {

//             var s1 = Student.Builder()
//                     .setId(new StudentId(studentRepository.getNextId().isPresent()?studentRepository.getNextId().get():studentRepository.minId))
//                     .setName("danny1")
//                     .setPassword("123")
//                     .build();
            var s1 = Student.Builder()
                    .setId(new StudentId(studentRepository.minId))
                    .setName("danny1")
                    .setPassword("123")
                    .build();

            var s2 = Student.Builder()
                    .setId(new StudentId(studentRepository.minId + 1))
                    .setName("danny2")
                    .setPassword("123")
                    .build();
            studentRepository.save(s1);
            studentRepository.save(s2);

            var t1 = Teacher.Builder()
                    .setId(new TeacherId(teacherRepository.minId))
                    .setName("t1")
                    .setPassword("123")
                    .build();
            var t2 = Teacher.Builder()
                    .setId(new TeacherId(teacherRepository.minId + 1))
                    .setName("t2")
                    .setPassword("12345")
                    .build();
            teacherRepository.save(t1);
            teacherRepository.save(t2);
            var course1 = Course.Builder()
                    .setId(new CourseId(courseRepository.minId))
                    .setPassword("123456")
                    .setTitle("c1")
                    .setTeacher(t1)
                    .build();
            var course2 = Course.Builder()
                    .setId(new CourseId(courseRepository.minId + 1))
                    .setPassword("123")
                    .setTitle("c2")
                    .setTeacher(t1)
                    .build();
            courseRepository.save(course1);
            courseRepository.save(course2);

            var exam1 = Exam.Builder()
                    .setId(new ExamId(examRepository.minId, course1.getId().getCourseId()))
                    .setTitle("exam1")
                    .setChoiceNum(3)
                    .setCourse(course1)
                    .build();
            var exam2 = Exam.Builder()
                    .setId(new ExamId(examRepository.minId + 1, course1.getId().getCourseId()))
                    .setTitle("exam2")
                    .setChoiceNum(4)
                    .setCourse(course1)
                    .build();
            examRepository.save(exam1);
//            examRepository.save(exam2);
            var notice1 = Notice.Builder()
                    .setId(new NoticeId(noticeRepository.minId, course1.getId().getCourseId()))
                    .setCourse(course1)
                    .setContent("开课了")
                    .build();
            var notice2 = Notice.Builder()
                    .setId(new NoticeId(noticeRepository.minId + 1, course1.getId().getCourseId()))
                    .setCourse(course1)
                    .setContent("开课了111")
                    .build();
            noticeRepository.save(notice1);
            noticeRepository.save(notice2);

            var stucourse1 = StuTakeCourse.Builder()
                    .setId(new StuTakeCourseId(s1.getId().getStudentId(), course1.getId().getCourseId()))
                    .setCourse(course1)
                    .setStudent(s1)
                    .build();
            course1.getStuTakeCourses().add(stucourse1);
            s1.getStuTakeCourses().add(stucourse1);
            courseRepository.save(course1);
            studentRepository.save(s1);
            System.out.println(course1.getStuTakeCourses().toString());
            System.out.println(s1.getStuTakeCourses().toString());

            //stuTakeCourseRepository.save(stucourse1);

//            var examinfo1 = ExamInfo.Builder()
//                    .setId(new ExamInfoId(s1.getId().getStudentId(), exam1.getId().getExamId(), exam1.getId().getCourseId()))
//                    .setScore(100)
//                    .setState(1)
//                    .setExam(exam1)
//                    .setStudent(s1)
//                    .build();
//            examInfoRepository.save(examinfo1);
//
//            var q1 = Question.Builder()
//                    .setId(new QuestionId(questionRepository.minId))
//                    .setCourse(course1)
//                    .setDescription("12334546")
//                    .build();
//            questionRepository.save(q1);
//
//            var ep = ExaminationPaper.Builder()
//                    .setId(new ExaminationPaperId(s1.getId().getStudentId(), exam1.getId().getExamId(), exam1.getId().getCourseId(), q1.getId().getQuestionId()))
//                    .setStudent(s1)
//                    .setExam(exam1)
//                    .setQuestion(q1).build();
//            examinationPaperRepository.save(ep);

            //**********************************添加考试测试***********************************

            //正常工作逻辑 Optional.get取值需要将对应的懒加载取消，设置方法为fetch=FetchType.EAGER
//            Optional<Course> course = courseRepository.findById(new CourseId(courseRepository.minId));
//            if(course.isPresent()){
//                Course myCourse=course.get();
//                myCourse.getExamSet().add(exam2);
//                courseRepository.save(myCourse);
//            }
            //**********************************添加考试测试***********************************

            //**********************************级联删除测试***********************************
//            studentRepository.deleteById(s1.getId());
//            courseRepository.deleteById(course1.getId());

            //***********************************级联删除测试**********************************


//            var q1 = Question.Builder()
//                    .setId(new QuestionId(questionRepository.minId))
//                    .setCourse(course1)
//                    .setDescription("12334546")
//                    .build();
//
//            var o1 = Option.Builder()
//                    .setId(new OptionId(optionRepository.minId, q1.getId().getQuestionId()))
//                    .setContent("234345")
//                    .setQuestion(q1)
//                    .build();
//            var o2 = Option.Builder()
//                    .setId(new OptionId(optionRepository.minId + 1, q1.getId().getQuestionId()))
//                    .setContent("234345534")
//                    .setQuestion(q1)
//                    .build();
//
//            var q2 = Question.Builder()
//                    .setId(new QuestionId(questionRepository.minId + 1))
//                    .setCourse(course1)
//                    .setDescription("12334546123")
//                    .build();
//

//
//            var stucourse2 = StuTakeCourse.Builder()
//                    .setId(new StuTakeCourseId(s1.getId().getStudentId(),course2.getId().getCourseId()))
//                    .build();
//            var stucourse3 = StuTakeCourse.Builder()
//                    .setId(new StuTakeCourseId(s2.getId().getStudentId(),course1.getId().getCourseId()))
//                    .build();
//            var stucourse4 = StuTakeCourse.Builder()
//                    .setId(new StuTakeCourseId(s2.getId().getStudentId(),course2.getId().getCourseId()))
//                    .build();
//

//            stuTakeCourseRepository.save(stucourse2);
//            stuTakeCourseRepository.save(stucourse3);
//            stuTakeCourseRepository.save(stucourse4);
//
//
//            var examinfo1 = ExamInfo.Builder()
//                    .setId(new ExamInfoId(exam1.getId().getExamId(), exam1.getId().getCourseId(), s1.getId().getStudentId()))
//                    .setScore(100)
//                    .setState(1)
//                    .build();
//
//            var examinfo2 = ExamInfo.Builder()
//                    .setId(new ExamInfoId(exam2.getId().getExamId(), exam2.getId().getCourseId(), s1.getId().getStudentId()))
//                    .setScore(100)
//                    .setState(1)
//                    .build();
//
//            q1.getOptionSet().add(o1);
//            q1.getOptionSet().add(o2);
//            course1.getQuestionSet().add(q1);
//            course1.getQuestionSet().add(q2);
//            course1.getExamSet().add(exam1);
//            course1.getExamSet().add(exam2);
//            course1.getNoticeSet().add(notice1);
//            course1.getNoticeSet().add(notice2);
//            course1.getStuTakeCourses().add(stucourse1);
//            course1.getStuTakeCourses().add(stucourse3);
//            course2.getStuTakeCourses().add(stucourse2);
//            course2.getStuTakeCourses().add(stucourse4);
//            s1.getStuTakeCourses().add(stucourse1);
//            s1.getStuTakeCourses().add(stucourse2);
//            s2.getStuTakeCourses().add(stucourse3);
//            s2.getStuTakeCourses().add(stucourse4);
//            studentRepository.save(s1);
//            studentRepository.save(s2);
//
//            t1.getCourseSet().add(course1);
//            t1.getCourseSet().add(course2);
//            teacherRepository.save(t1);
//            var t2 = Teacher.Builder()
//                    .setId(new TeacherId(teacherRepository.minId + 1))
//                    .setName("t2")
//                    .setPassword("123")
//                    .build();
//            teacherRepository.save(t2);


            //
            // var notice1 = Notice.Builder().setContent("312").setCourse(course1).build();


//            teacherRepository.save(t1);
//            teacherRepository.save(t2);

            // teacherRepository.deleteById(new TeacherId(2L));


        };
    }


}
