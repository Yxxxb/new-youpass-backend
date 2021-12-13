package com.youpass.service.impl;

import com.youpass.dao.CourseRepository;
import com.youpass.dao.StuTakeCourseRepository;
import com.youpass.dao.StudentRepository;
import com.youpass.dao.TeacherRepository;
import com.youpass.pojo.Course;
import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.StudentId;
import com.youpass.pojo.pk.TeacherId;
import com.youpass.service.CourseService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             TeacherRepository teacherRepository,
                             StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Result<Object> deleteCourse(Long teacherId, Long courseId) {
        if(courseRepository.existsById(new CourseId(courseId))){
            var course = courseRepository.findById(new CourseId(courseId)).get();
            if(course.getTeacher().getId().getTeacherId().equals(teacherId)){
                var teacher = teacherRepository.findById(new TeacherId(teacherId)).get();
                teacher.getCourseSet().remove(course);
                courseRepository.delete(course);
                teacherRepository.save(teacher);
                return ResultUtil.success();
            }else{
                return ResultUtil.error(ResultEnum.PERMISSION_DENIED);
            }
        }else{
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        }
    }

    @Override
    public Result<Object> getCourseById(Long courseId) {
        if(courseRepository.existsById(new CourseId(courseId))){
            var course = courseRepository.findById(new CourseId(courseId)).get();
            return ResultUtil.success(course);
        }else{
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        }
    }

    @Override
    public Result<Object> getCourseByTitle(String title) {
        return ResultUtil.success(courseRepository.findCourseByTitle(title));
    }

    @Override
    public Result<Object> getCourseByTName(String teacherName) {
        return ResultUtil.success(teacherRepository.findTeacherByName(teacherName));
    }

    @Override
    public Result<Object> getCourseOfUser(Long id) {
        if(teacherRepository.existsById(new TeacherId(id))){
            System.out.println("老师");
            var teacher = teacherRepository.findById(new TeacherId(id)).get();
            return ResultUtil.success(teacher.getCourseSet());
        }else if(studentRepository.existsById(new StudentId(id))){
            System.out.println("学生");
            var student = studentRepository.findById(new StudentId(id)).get();
            List<Course> courseList = new ArrayList<>();
            for(var stuCourse:student.getStuTakeCourses()){
                courseList.add(stuCourse.getCourse());
            }
            return ResultUtil.success(courseList);
        }else {
            return ResultUtil.error(ResultEnum.USER_MISS);
        }
    }
}
