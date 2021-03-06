package com.youpass.controller;

import com.youpass.model.CourseInfo;
import com.youpass.model.CourseInfo2;
import com.youpass.model.CourseInfo3;
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
    public Result<Object> createCourse(@RequestAttribute(name = "id") Long teacherId,@RequestBody CourseInfo2 courseInfo){
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
    public Result<Object> joinCourse(@RequestAttribute(name ="id") Long studentId,@RequestBody CourseInfo3 courseInfo){
        return courseService.joinCourse(studentId,courseInfo);

    }

}
