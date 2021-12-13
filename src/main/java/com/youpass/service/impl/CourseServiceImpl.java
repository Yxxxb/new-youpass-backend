package com.youpass.service.impl;

import com.youpass.dao.CourseRepository;
import com.youpass.dao.StuTakeCourseRepository;
import com.youpass.dao.StudentRepository;
import com.youpass.dao.TeacherRepository;
import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.TeacherId;
import com.youpass.service.CourseService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final TeacherRepository teacherRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
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
            }else{
                return ResultUtil.error(ResultEnum.PERMISSION_DENIED);
            }
        }else{
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        }
        return null;
    }
}
