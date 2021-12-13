package com.youpass.service.impl;

import com.youpass.dao.CourseRepository;
import com.youpass.dao.ExamInfoRepository;
import com.youpass.dao.StudentRepository;
import com.youpass.model.ExamReturnInfo;
import com.youpass.model.ReleaseExamInfo;
import com.youpass.model.SetSessionInfo;
import com.youpass.pojo.Exam;
import com.youpass.pojo.ExamInfo;
import com.youpass.pojo.pk.CourseId;
import com.youpass.pojo.pk.ExamId;
import com.youpass.pojo.pk.StudentId;
import com.youpass.service.ExamService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ExamInfoRepository examInfoRepository;

    @Autowired
    public ExamServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, ExamInfoRepository examInfoRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.examInfoRepository = examInfoRepository;
    }

    @Override
    public Result<Object> GetExamsByCourseId(Long courseIdGet) {
        if (courseIdGet == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        if (courseRepository.existsById(new CourseId(courseIdGet))) {
            CourseId courseId = new CourseId(courseIdGet);
            var course = courseRepository.findById(courseId).get();
            List<ExamReturnInfo> exams = new ArrayList<>();
            for (Exam e : course.getExamSet()) {
                exams.add(new ExamReturnInfo(e.getId(), e.getStart_time(), e.getEnd_time(), e.getTitle()));
            }
            return ResultUtil.success(exams);
        } else {
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        }
    }

    @Override
    public Result<Object> GetExamByStudentId(Long studentIdGet) {
        StudentId studentId = new StudentId(studentIdGet);
        var student = studentRepository.findById(studentId).get();
        List<ExamReturnInfo> exams = new ArrayList<>();
        for (ExamInfo s : student.getExamInfos()) {
            exams.add(new ExamReturnInfo(
                    s.getExam().getId(),
                    s.getExam().getStart_time(),
                    s.getExam().getEnd_time(),
                    s.getExam().getTitle()));
        }
        return ResultUtil.success(exams);
    }

    @Override
    public Result<Object> ReleaseTest(Long studentIdGet, ReleaseExamInfo releaseExamInfo) {


        return ResultUtil.success();
    }

    @Override
    public Result<Object> SetSession(HttpServletRequest request, Long studentIdGet, SetSessionInfo setSessionInfo) throws ParseException {
        StudentId studentId = new StudentId(studentIdGet);
        ExamId examId = new ExamId(setSessionInfo.getExamId());
        CourseId courseId = new CourseId(setSessionInfo.getCourseId());

        var student = studentRepository.findById(studentId).get();
        Exam exam = new Exam();
        ExamInfo examInfo = new ExamInfo();
        for (ExamInfo s : student.getExamInfos()) {
            if (s.getExam().getCourse().getId() == courseId && s.getExam().getId() == examId) {
                exam = s.getExam();
                examInfo = s;
                break;
            }
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startTime = exam.getStart_time();
        Date endTime = exam.getEnd_time();
        Date date = new Date();
        String nowTimeString = formatter.format(new Date());
        Date nowTime = formatter.parse(nowTimeString);

        if ((nowTime.before(startTime)) && ((nowTime.after(endTime)))) {
            HttpSession session = request.getSession(true);
            session.setAttribute("exam_id", setSessionInfo.getExamId());
            session.setAttribute("course_id", setSessionInfo.getCourseId());

            if(examInfo.getState()==0 || examInfo.getState()==1){
                examInfo.setState(1);
                examInfoRepository.save(examInfo);
            }
            return ResultUtil.success();
        } else {
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        }
    }

    @Override
    public Result<Object> CheckState(Long id) {
        //因为有拦截器 所以到了这里就一定可以
        return ResultUtil.success();
    }
}
