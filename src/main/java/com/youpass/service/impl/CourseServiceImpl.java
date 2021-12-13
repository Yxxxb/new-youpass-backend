package com.youpass.service.impl;

import com.youpass.dao.*;
import com.youpass.model.CourseInfo;
import com.youpass.pojo.Course;
import com.youpass.pojo.Notice;
import com.youpass.pojo.Teacher;
import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.TeacherId;
import com.youpass.service.CourseService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final TeacherRepository teacherRepository;

    private final NoticeRepository noticeRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             TeacherRepository teacherRepository,
                             NoticeRepository noticeRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.noticeRepository = noticeRepository;
    }

    @Override
    public Result<Object> deleteCourse(Long teacherId, Long courseId) {
        if (courseRepository.existsById(new CourseId(courseId))) {
            var course = courseRepository.findById(new CourseId(courseId)).get();
            if (course.getTeacher().getId().getTeacherId().equals(teacherId)) {
                var teacher = teacherRepository.findById(new TeacherId(teacherId)).get();
                teacher.getCourseSet().remove(course);
                courseRepository.delete(course);
                teacherRepository.save(teacher);
            } else {
                return ResultUtil.error(ResultEnum.PERMISSION_DENIED);
            }
        } else {
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        }
        return null;
    }

    @Override
    public Result<Object> createCourse(Long teacherId, CourseInfo courseInfo) {
        if (teacherId == null ||
                courseInfo.getTitle() == null ||
                courseInfo.getPassword() == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        Long courseId = courseRepository.getNextId().orElse(courseRepository.minId);
        Optional<Teacher> teacherOptional = teacherRepository.findById(new TeacherId(teacherId));
        if (teacherOptional.isEmpty()) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        Teacher teacher = teacherOptional.get();
        Course course = Course.Builder()
                .setId(new CourseId(courseId))
                .setPassword(courseInfo.getPassword())
                .setTitle(courseInfo.getTitle())
                .setTeacher(teacher).build();

        Long noticeId = noticeRepository.getNextId(new CourseId(courseId)).orElse(noticeRepository.minId);

//        SimpleDateFormat dateFormat =new SimpleDateFormat("YYYY-MM-dd");
//        String date = dateFormat.format(new Date());
        Notice notice = Notice.Builder()
                .setId(noticeId)
                .setCourse(course)
                .setContent(courseInfo.getTitle() + "开课啦！")
                .setTime(new Date()).build();

        course.getNoticeSet().add(notice);
        teacher.getCourseSet().add(course);
        teacherRepository.save(teacher);
        return ResultUtil.success("创建课程并发布通知成功！");

    }
}
