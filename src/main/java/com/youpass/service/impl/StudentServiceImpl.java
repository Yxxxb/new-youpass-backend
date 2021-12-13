package com.youpass.service.impl;

import com.youpass.dao.CourseRepository;
import com.youpass.dao.StuTakeCourseRepository;
import com.youpass.dao.StudentRepository;
import com.youpass.model.StuInfoOfCourse;
import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.StuTakeCourseId;
import com.youpass.pojo.pk.StudentId;
import com.youpass.service.StudentService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    private final StuTakeCourseRepository stuTakeCourseRepository;

    @Autowired
    public StudentServiceImpl(CourseRepository courseRepository,
                              StuTakeCourseRepository stuTakeCourseRepository,
                              StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.stuTakeCourseRepository = stuTakeCourseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Result<Object> getStuInfoOfCourse(Long id, Long courseId) {
        if (courseRepository.existsById(new CourseId(courseId))) {
            var course = courseRepository.findById(new CourseId(courseId)).get();
            if(course.getTeacher().getId().getTeacherId().equals(id)){
                List<StuInfoOfCourse> stuInfoList = new ArrayList<>();
                //获取所有的学生
                for(var stuCourse : course.getStuTakeCourses()){
                    var student = stuCourse.getStudent();
                    var tempStuInfo = new StuInfoOfCourse();
                    tempStuInfo.setId(student.getId().getStudentId());
                    tempStuInfo.setName(student.getName());
                    for(var stuExam : student.getExamInfos()){
                        var exam = stuExam.getExam();

                        StuInfoOfCourse.StuInfoOfExam stuInfoOfExam = new StuInfoOfCourse.StuInfoOfExam();
                        stuInfoOfExam.setExamId(exam.getId().getExamId());
                        stuInfoOfExam.setNature(exam.getNature());
                        stuInfoOfExam.setState(stuExam.getState());
                        stuInfoOfExam.setTitle(exam.getTitle());
                        stuInfoOfExam.setScore(stuExam.getScore());

                        tempStuInfo.getExamInfo().add(stuInfoOfExam);
                    }
                    stuInfoList.add(tempStuInfo);
                }
                return ResultUtil.success(stuInfoList);
            }else{
                return ResultUtil.error(ResultEnum.PERMISSION_DENIED);
            }
        } else {
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        }
    }

    @Override
    @Transactional
    public Result<Object> deleteStudentFromCourse(Long teacherId, Long studentId,Long courseId) {
        if(courseRepository.existsById(new CourseId(courseId))){
            var course = courseRepository.findById(new CourseId(courseId)).get();
            if(course.getTeacher().getId().getTeacherId().equals(teacherId)){
                if(!stuTakeCourseRepository.existsById(new StuTakeCourseId(studentId,courseId))){
                    return ResultUtil.error(ResultEnum.USER_MISS);
                }
                var student  = studentRepository.findById(new StudentId(studentId)).get();
                //执行删除逻辑
                var stuCourse = stuTakeCourseRepository.findById(new StuTakeCourseId(studentId,courseId)).get();

                student.getStuTakeCourses().remove(stuCourse);
                course.getStuTakeCourses().remove(stuCourse);

                stuTakeCourseRepository.delete(stuCourse);

                studentRepository.save(student);
                courseRepository.save(course);

                return ResultUtil.success();
            }else{
                return ResultUtil.error(ResultEnum.PERMISSION_DENIED);
            }
        }else{
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        }
    }
}
