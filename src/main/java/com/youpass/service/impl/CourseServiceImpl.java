package com.youpass.service.impl;

import com.youpass.dao.*;
import com.youpass.model.CourseInfo;
import com.youpass.pojo.*;
import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.StuTakeCourseId;
import com.youpass.pojo.pk.StudentId;
import com.youpass.pojo.pk.TeacherId;
import com.youpass.service.CourseService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final TeacherRepository teacherRepository;

    private final NoticeRepository noticeRepository;

    private final StudentRepository studentRepository;

    private final StuTakeCourseRepository stuTakeCourseRepository;
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             TeacherRepository teacherRepository,
                             NoticeRepository noticeRepository, StudentRepository studentRepository, StuTakeCourseRepository stuTakeCourseRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.noticeRepository = noticeRepository;
        this.studentRepository = studentRepository;
        this.stuTakeCourseRepository = stuTakeCourseRepository;
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

    @Override
    public Result<Object> joinCourse(Long studentId, CourseInfo courseInfo) {
        if(studentId==null||
        courseInfo.getCourseId()==null||
        courseInfo.getPassword()==null){
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        //查询该studentId是否已经在课程列表中
        Optional<Student> studentOptional = studentRepository.findById(new StudentId(studentId));
        if(studentOptional.isEmpty()){
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        Set<StuTakeCourse> stuTakeCourseSet= studentOptional.get().getStuTakeCourses();
        for(StuTakeCourse stuTakeCourse:stuTakeCourseSet){
            if(stuTakeCourse.getCourse().getId().getCourseId().equals(courseInfo.getCourseId())){
                return ResultUtil.error(ResultEnum.ALREADY_EXISTS);
            }
        }
        Optional<Course> courseOptional = courseRepository.findById(new CourseId(courseInfo.getCourseId()));
        if(courseOptional.isEmpty()){
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        //密码错误的情况
        if(!courseOptional.get().getPassword().equals(courseInfo.getPassword())){
            return ResultUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        //将数据插入到StuTakeCourse表中
        StuTakeCourse stuTakeCourse=StuTakeCourse.Builder()
                .setStudent(studentOptional.get())
                .setCourse(courseOptional.get())
                .setId(new StuTakeCourseId(studentId,courseInfo.getCourseId()))
                .build();
        stuTakeCourseRepository.save(stuTakeCourse);
        //因为已经发布的考试每位学生已经有题了，所以对于后加进来的学生，是没有对应的题的，因此也没有必要将他们加入到学生考试的表中
        return ResultUtil.success("加入课程成功");
    }
}
