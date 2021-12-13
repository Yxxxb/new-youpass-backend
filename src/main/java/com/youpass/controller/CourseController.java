package com.youpass.controller;

import com.youpass.model.CourseInfo;
import com.youpass.service.CourseService;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @DeleteMapping(path = "deleteCourse")
    public Result<Object> DeleteCourse(@RequestAttribute(name = "id") Long id, @RequestParam("courseId") Long courseId){
        return courseService.deleteCourse(id,courseId);
    }


    @PostMapping(path = "createCourse")
    public Result<Object> createCourse(@RequestAttribute(name = "id") Long teacherId,@RequestBody CourseInfo courseInfo){
        return courseService.createCourse(teacherId,courseInfo);
    }

}
