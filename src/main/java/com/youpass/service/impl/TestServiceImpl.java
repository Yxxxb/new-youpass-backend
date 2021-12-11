package com.youpass.service.impl;

import com.youpass.dao.CourseRepository;
import com.youpass.dao.StudentRepository;
import com.youpass.dao.TeacherRepository;
import com.youpass.pojo.Course;
import com.youpass.pojo.pk.TeacherId;
import com.youpass.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public TestServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    //测试删除老师是否成功
    public void deleteTeacher(Long id) {
        boolean exists = teacherRepository.existsById(new TeacherId(id));
        if (!exists) {
            throw new IllegalStateException("teacher with id " + id + " does not exists");
        }
        teacherRepository.deleteById(new TeacherId(id));
    }
//    public void findCourseByTeacherId(Long teacherId){
//        List<Course> courseList = courseRepository.findAll();
//        for(Course item:courseList){
//            item.getTeacher().
//        }
//
//    }
}
