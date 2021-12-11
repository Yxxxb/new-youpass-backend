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

            var t1 = Teacher.Builder()
                    .setId(new TeacherId(teacherRepository.minId))
                    .setName("t1")
                    .setPassword("123")
                    .build();
            var course1 = Course.Builder()
                    .setId(new CourseId(courseRepository.minId))
                    .setPassword("123")
                    .setTitle("c1")
                    .setTeacher(t1)
                    .build();
            var course2 = Course.Builder()
                    .setId(new CourseId(courseRepository.minId + 1))
                    .setPassword("123")
                    .setTitle("c2")
                    .setTeacher(t1)
                    .build();
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
            var q1 = Question.Builder()
                    .setId(new QuestionId(questionRepository.minId))
                    .setCourse(course1)
                    .setDescription("12334546")
                    .build();

            var o1 = Option.Builder()
                    .setId(new OptionId(optionRepository.minId, q1.getId().getQuestionId()))
                    .setContent("234345")
                    .setQuestion(q1)
                    .build();
            var o2 = Option.Builder()
                    .setId(new OptionId(optionRepository.minId + 1, q1.getId().getQuestionId()))
                    .setContent("234345534")
                    .setQuestion(q1)
                    .build();

            var q2 = Question.Builder()
                    .setId(new QuestionId(questionRepository.minId + 1))
                    .setCourse(course1)
                    .setDescription("12334546123")
                    .build();

            var stucourse1 = StuTakeCourse.Builder()
//                    .setId(new StuTakeCourseId(s1.getId().getStudentId(),course1.getId().getCourseId()))
                    .setStudent(s1)
                    .setCourse(course1)
                    .build();

            var stucourse2 = StuTakeCourse.Builder()
//                    .setId(new StuTakeCourseId(s1.getId().getStudentId(),course2.getId().getCourseId()))
                    .setStudent(s1)
                    .setCourse(course2)
                    .build();
            var stucourse3 = StuTakeCourse.Builder()
//                    .setId(new StuTakeCourseId(s2.getId().getStudentId(),course1.getId().getCourseId()))
                    .setStudent(s2)
                    .setCourse(course1)
                    .build();
            var stucourse4 = StuTakeCourse.Builder()
//                    .setId(new StuTakeCourseId(s2.getId().getStudentId(),course2.getId().getCourseId()))
                    .setStudent(s2)
                    .setCourse(course2)
                    .build();

            teacherRepository.save(t1);
            studentRepository.save(s1);
            studentRepository.save(s2);

            q1.getOptionSet().add(o1);
            q1.getOptionSet().add(o2);
            course1.getQuestionSet().add(q1);
            course1.getQuestionSet().add(q2);
            course1.getExamSet().add(exam1);
            course1.getExamSet().add(exam2);
            course1.getNoticeSet().add(notice1);
            course1.getNoticeSet().add(notice2);
            course1.getStuTakeCourses().add(stucourse1);
            course1.getStuTakeCourses().add(stucourse3);
            course2.getStuTakeCourses().add(stucourse2);
            course2.getStuTakeCourses().add(stucourse4);
            s1.getStuTakeCourses().add(stucourse1);
            s1.getStuTakeCourses().add(stucourse2);
            s2.getStuTakeCourses().add(stucourse3);
            s2.getStuTakeCourses().add(stucourse4);
            t1.getCourseSet().add(course1);
            t1.getCourseSet().add(course2);
            teacherRepository.save(t1);
            studentRepository.save(s1);
            studentRepository.save(s2);



            var examinfo1 = ExamInfo.Builder()
                    .setId(new ExamInfoId(exam1.getId().getExamId(), exam1.getId().getCourseId(), s1.getId().getStudentId()))
                    .setScore(100)
                    .setState(1)
                    .setExam(exam1)
                    .setStudent(s1)
                    .build();

            var examinfo2 = ExamInfo.Builder()
                    .setId(new ExamInfoId(exam2.getId().getExamId(), exam2.getId().getCourseId(), s1.getId().getStudentId()))
                    .setScore(100)
                    .setState(1)
                    .setExam(exam2)
                    .setStudent(s1)
                    .build();

            exam1.getExamInfoSet().add(examinfo1);
            exam2.getExamInfoSet().add(examinfo2);
            s1.getExamInfos().add(examinfo1);
            s1.getExamInfos().add(examinfo2);
            courseRepository.save(course1);
            studentRepository.save(s1);

            var ep1= ExaminationPaper.Builder()
                    .setId(new ExaminationPaperId(s1.getId().getStudentId(),exam1.getId().getExamId(),exam1.getId().getCourseId(),q1.getId().getQuestionId()))
                    .setQuestion(q1)
                    .setExam(exam1)
                    .setStudent(s1)
                    .setNumInPaper(12)
                    .setSelfOrder(1)
                    .setValue(14)
                    .build();
            var ep2= ExaminationPaper.Builder()
                    .setId(new ExaminationPaperId(s2.getId().getStudentId(),exam1.getId().getExamId(),exam1.getId().getCourseId(),q1.getId().getQuestionId()))
                    .setQuestion(q1)
                    .setExam(exam1)
                    .setStudent(s2)
                    .setNumInPaper(12)
                    .setSelfOrder(1)
                    .setValue(14)
                    .build();

            s1.getExaminationPaperSet().add(ep1);
            s2.getExaminationPaperSet().add(ep2);
            exam1.getExaminationPaperSet().add(ep1);
            exam1.getExaminationPaperSet().add(ep2);
            q1.getExaminationPaperSet().add(ep1);
            q1.getExaminationPaperSet().add(ep2);
            studentRepository.save(s1);
            studentRepository.save(s2);
            courseRepository.save(course1);

//            var course = courseRepository.getById(new CourseId(1000L));
//            for (var item : course.getStuTakeCourses()){
//                System.out.println(item.getId().getCourseId().getCourseId().toString()+" "+item.getId().getStudentId().getStudentId().toString());
//            }


            /**
             * 查询
             */
//            var teacher = teacherRepository.findById(new TeacherId(10500L));
//            for(var item : teacher.get().getCourseSet()){
//                System.out.println(item.getTitle());
//            }


            /**
             *更新
             */
            var teacher = teacherRepository.findById(new TeacherId(10500L)).orElseThrow(()->new IllegalStateException("123"));
            teacher.setName("danny");
            teacherRepository.save(teacher);


            /**
             * 删除
             */
            teacherRepository.deleteById(new TeacherId(10500L));














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



//            var t1 = Teacher.Builder()
//                    .setId(new TeacherId(teacherRepository.minId))
//                    .setName("t1")
//                    .setPassword("123")
//                    .build();
//            var course1 = Course.Builder()
//                    .setId(new CourseId(courseRepository.minId))
//                    .setPassword("123")
//                    .setTitle("c1")
//                    .setTeacher(t1)
//                    .build();
//            var course2 = Course.Builder()
//                    .setId(new CourseId(courseRepository.minId + 1))
//                    .setPassword("123")
//                    .setTitle("c2")
//                    .setTeacher(t1)
//                    .build();
//            teacherRepository.save(t1);
//            courseRepository.save(course1);
//            courseRepository.save(course2);
//            Teacher teacherRepositoryById = teacherRepository.getById(t1.getId());
//            var courseSet = teacherRepositoryById.getCourseSet();
////            for(var item:teacherRepositoryById.getCourseSet()){
////                System.out.println(item.getId().getCourseId());
////                System.out.println(item.getTitle());
//            }
        };
    }


}
