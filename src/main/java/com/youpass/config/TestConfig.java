package com.youpass.config;

import com.youpass.dao.*;
import com.youpass.pojo.ExaminationPaper;
import com.youpass.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    CommandLineRunner commandLineRunner() {
        return args -> {
        };
    }


}
