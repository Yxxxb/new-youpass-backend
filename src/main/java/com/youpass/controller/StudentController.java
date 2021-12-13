package com.youpass.controller;

import com.youpass.service.StudentService;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 老师获得一门课程中所有学生的基本信息和对应的考试信息
     *
     * @param id       从session获得的teacher id
     * @param courseId 从params 获得的 courseId
     * @return {
     * "code": 100,
     * "msg": "成功",
     * "data": [
     * {
     * "id": 1950001,
     * "name": "danny2",
     * "examInfo": []
     * },
     * {
     * "id": 1950000,
     * "name": "danny1",
     * "examInfo": [
     * {
     * "examId": 1,
     * "nature": null,
     * "title": "exam1",
     * "score": 100,
     * "state": 1
     * },
     * {
     * "examId": 2,
     * "nature": null,
     * "title": "exam2",
     * "score": 100,
     * "state": 1
     * }
     * ]
     * }
     * ]
     * }
     */
    @GetMapping(path = "getStuInfoOfCourse")
    public Result<Object> GetStuInfoOfCourse(@RequestAttribute(name = "id") Long id, @RequestParam("courseId") Long courseId) {
        return this.studentService.getStuInfoOfCourse(id, courseId);
    }

    /**
     * 删除课程里的学生
     *
     * @param teacherId 通过session获得的老师id
     * @param studentId 通过params传入的要被删除的学生id
     * @param courseId  通过params传入的课程id
     * @return {
     * "code": 100,
     * "msg": "成功",
     * "data": null
     * }
     */
    @DeleteMapping(path = "deleteStudent")
    public Result<Object> DeleteStudentFromCourse(@RequestAttribute(name = "id") Long teacherId, @RequestParam("studentId") Long studentId, @RequestParam("courseId") Long courseId) {
        return this.studentService.deleteStudentFromCourse(teacherId, studentId, courseId);
    }


}
