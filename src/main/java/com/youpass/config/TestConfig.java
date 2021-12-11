package com.youpass.config;

import com.youpass.dao.*;
import com.youpass.pojo.*;
import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.StudentId;
import com.youpass.pojo.pk.TeacherId;
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

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner() {
        return args -> {

             var s1 = Student.Builder()
                     .setId(new StudentId(studentRepository.getNextId().isPresent()?studentRepository.getNextId().get():studentRepository.minId))
                     .setName("danny1")
                     .setPassword("123")
                     .build();
             studentRepository.save(s1);
             var s2 = Student.Builder()
                     .setId(new StudentId(studentRepository.getNextId().isPresent()?studentRepository.getNextId().get():studentRepository.minId))
                     .setName("danny2")
                     .setPassword("123")
                     .build();
            studentRepository.save(s2);

            var t1 = Teacher.Builder()
                    .setId(new TeacherId(teacherRepository.getNextId().isPresent()?teacherRepository.getNextId().get():teacherRepository.minId))
                    .setName("t1")
                    .setPassword("123")
                    .build();
            var course1 = Course.Builder()
                    .setId(new CourseId(courseRepository.getNextId().isPresent()?courseRepository.getNextId().get():courseRepository.minId))
                    .setPassword("123")
                    .setTitle("c1")
                    .setTeacher(t1)
                    .build();
            var course2 = Course.Builder()
                    .setId(new CourseId(courseRepository.getNextId().isPresent()?courseRepository.getNextId().get():courseRepository.minId))
                    .setPassword("123")
                    .setTitle("c2")
                    .setTeacher(t1)
                    .build();
            t1.getCourseSet().add(course1);
            t1.getCourseSet().add(course2);
            teacherRepository.save(t1);
            var t2 = Teacher.Builder()
                    .setId(new TeacherId(teacherRepository.getNextId().isPresent()?teacherRepository.getNextId().get():teacherRepository.minId))
                    .setName("t2")
                    .setPassword("123")
                    .build();
            teacherRepository.save(t2);


            //
            // var notice1 = Notice.Builder().setContent("312").setCourse(course1).build();


//            teacherRepository.save(t1);
//            teacherRepository.save(t2);

            // teacherRepository.deleteById(new TeacherId(2L));
        };
    }


}
