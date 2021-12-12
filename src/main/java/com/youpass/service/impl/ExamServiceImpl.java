package com.youpass.service.impl;

import com.youpass.dao.CourseRepository;
import com.youpass.model.ExamReturnInfo;
import com.youpass.pojo.Exam;
import com.youpass.pojo.pk.CourseId;
import com.youpass.service.ExamService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    private final CourseRepository courseRepository;

    @Autowired
    public ExamServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Result<Object> GetExamsByCourseId(Long courseIdGet) {
        if (courseIdGet==null){
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        if(courseRepository.existsById(new CourseId(courseIdGet))){
            CourseId courseId = new CourseId(courseIdGet);
            var course = courseRepository.findById(courseId).get();
//            return ResultUtil.success(course.getExamSet());
            List<ExamReturnInfo> exams = new ArrayList<>();
            for (Exam e : course.getExamSet()){
                exams.add(new ExamReturnInfo(e.getId(), e.getStart_time(), e.getEnd_time(), e.getTitle()));
            }
            return ResultUtil.success(exams);
        }else {
            return ResultUtil.error(ResultEnum.COURSE_MISS);
        }
    }
}
