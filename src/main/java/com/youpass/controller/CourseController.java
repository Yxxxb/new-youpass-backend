package com.youpass.controller;

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


    /**
     * 老师删除课程
     *
     * @param id       从session获得的teacher id
     * @param courseId 从params中传来的课程id
     * @return{ "code": 100,
     * "msg": "成功",
     * "data": null
     * }
     */
    @DeleteMapping(path = "deleteCourse")
    public Result<Object> DeleteCourse(@RequestAttribute(name = "id") Long id, @RequestParam("courseId") Long courseId) {
        return courseService.deleteCourse(id, courseId);
    }

    /**
     * 通过课程id获得课程信息
     *
     * @param courseId 从params中传来的课程id
     * @return{ "code": 100,
     * "msg": "成功",
     * "data": {
     * "id": {
     * "courseId": 1001
     * },
     * "title": "c2",
     * "teacher": {
     * "id": {
     * "teacherId": 10500
     * },
     * "name": "danny"
     * }
     * }
     * }
     */
    @GetMapping(path = "getCourseById")
    public Result<Object> GetCourseById(@RequestParam("courseId") Long courseId) {
        return courseService.getCourseById(courseId);
    }

    /**
     * 通过课程名获得课程
     *
     * @param title 课程的名字 在params中
     * @return {
     * "code": 100,
     * "msg": "成功",
     * "data": [
     * {
     * "id": {
     * "courseId": 1000
     * },
     * "title": "c1",
     * "teacher": {
     * "id": {
     * "teacherId": 10500
     * },
     * "name": "danny"
     * }
     * }
     * ]
     * }
     */
    @GetMapping(path = "getCourseByTitle")
    public Result<Object> GetCourseByTitle(@RequestParam("title") String title) {
        return courseService.getCourseByTitle(title);
    }

    /**
     * 通过老师的名字获得课程
     *
     * @param teacherName 老师的名字 在params中
     * @return {
     * "code": 100,
     * "msg": "成功",
     * "data": [
     * {
     * "id": {
     * "teacherId": 10500
     * },
     * "name": "danny",
     * "email": null,
     * "location": null
     * }
     * ]
     * }
     */
    @GetMapping(path = "getCourseByTName")
    public Result<Object> GetCourseByTName(@RequestParam("teacherName") String teacherName) {
        return courseService.getCourseByTName(teacherName);
    }

    /**
     * 获得一个用户的所有课程 包括学生和老师
     *
     * @param id 通过session获得的id
     * @return {
     * "code": 100,
     * "msg": "成功",
     * "data": [
     * {
     * "id": {
     * "courseId": 1001
     * },
     * "title": "c2",
     * "teacher": {
     * "id": {
     * "teacherId": 10500
     * },
     * "name": "danny"
     * }
     * },
     * {
     * "id": {
     * "courseId": 1000
     * },
     * "title": "c1",
     * "teacher": {
     * "id": {
     * "teacherId": 10500
     * },
     * "name": "danny"
     * }
     * }
     * ]
     * }
     */
    @GetMapping(path = "getCourseOfUser")
    public Result<Object> GetCourseOfUser(@RequestAttribute(name = "id") Long id) {
        System.out.println("321123");
        return courseService.getCourseOfUser(id);
    }


}
