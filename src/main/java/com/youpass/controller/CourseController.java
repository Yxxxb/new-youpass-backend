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

    /**
     * 老师创建课程
     * @param teacherId 老师的id
     * @param courseInfo 要创建的课程的相关信息
     *     class CourseInfo{
     *         private String title;
     *         private String password;
     *         private Long courseId;
     *         }
     * @return
     * 成功情况
     *        {
     *        "code": 100,
     *        "msg": "成功",
     *        "data": 创建课程成功
     *        }
     */
    @PostMapping(path = "createCourse")
    public Result<Object> createCourse(@RequestAttribute(name = "id") Long teacherId,@RequestBody CourseInfo courseInfo){
        return courseService.createCourse(teacherId,courseInfo);
    }

    /**
     * 学生加入课程
     * @param studentId 学生的id
     * @param courseInfo 要加入的课程相关信息
     *     class CourseInfo{
     *         private String title;
     *         private String password;
     *         private Long courseId;
     *         }
     * @return
     * 成功情况
     *        {
     *        "code": 100,
     *        "msg": "成功",
     *        "data": 加入课程成功
     *        }     * @return
     */
    @PostMapping(path = "joinCourse")
    public Result<Object> joinCourse(@RequestAttribute(name ="id") Long studentId,@RequestBody CourseInfo courseInfo){
        return courseService.joinCourse(studentId,courseInfo);

    }

}
