package com.youpass.service.impl;

import com.youpass.dao.NoticeRepository;
import com.youpass.dao.StudentRepository;
import com.youpass.dao.TeacherRepository;
import com.youpass.model.NoticeInfo;
import com.youpass.pojo.Notice;
import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.StudentId;
import com.youpass.pojo.pk.TeacherId;
import com.youpass.service.NoticeService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final NoticeRepository noticeRepository;


    @Autowired
    public NoticeServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository, NoticeRepository noticeRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.noticeRepository = noticeRepository;
    }

    @Override
    @Transactional
    public Result<Object> getNotice(Long id) {
        if (studentRepository.existsById(new StudentId(id))) {
            var student = studentRepository.findById(new StudentId(id)).get();
            var courseSet = student.getStuTakeCourses();
            List<NoticeInfo> noticeList = new ArrayList<>();
            for (var stuTakeCourses : courseSet) {
                var course = stuTakeCourses.getCourse();
                var noticeSet = course.getNoticeSet();
                for (var notice : noticeSet) {
                    var tempNoticeInfo = new NoticeInfo();
                    tempNoticeInfo.setNoticeId(notice.getId().getNoticeId());
                    tempNoticeInfo.setContent(notice.getContent());
                    tempNoticeInfo.setCourseId(course.getId().getCourseId());
                    tempNoticeInfo.setCourseTitle(course.getTitle());
                    tempNoticeInfo.setTeacherId(course.getTeacher().getId().getTeacherId());
                    tempNoticeInfo.setTeacherName(course.getTeacher().getName());
                    tempNoticeInfo.setTime(notice.getTime());

                    noticeList.add(tempNoticeInfo);
                }
            }
            return ResultUtil.success(noticeList);
        } else {
            return ResultUtil.error(ResultEnum.NOT_STUDENT);
        }
    }

    @Override
    @Transactional
    public Result<Object> generateNotice(NoticeInfo noticeInfo) {
        if (noticeInfo.getTeacherId() == null
                || noticeInfo.getCourseId() == null
                || noticeInfo.getContent() == null
                || noticeInfo.getTime() == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        if (teacherRepository.existsById(new TeacherId(noticeInfo.getTeacherId()))) {
            var teacher = teacherRepository.findById(new TeacherId(noticeInfo.getTeacherId())).get();

            for (var course : teacher.getCourseSet()) {
                if (course.getId().getCourseId().equals(noticeInfo.getCourseId()) ) {
                    //添加notice
                    var notice = Notice.Builder()
                            .setId(noticeRepository.getNextId(new CourseId(noticeInfo.getCourseId())).orElse(noticeRepository.minId))
                            .setCourse(course)
                            .setTime(noticeInfo.getTime())
                            .setContent(noticeInfo.getContent())
                            .build();
                    course.getNoticeSet().add(notice);
                    teacherRepository.save(teacher);
                    return ResultUtil.success();
                }
            }
            //没有课程
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        } else {
            return ResultUtil.error(ResultEnum.NOT_TEACHER);
        }
    }
}
