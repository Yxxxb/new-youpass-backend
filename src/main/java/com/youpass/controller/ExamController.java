package com.youpass.controller;

import com.youpass.model.PostAnswerInfo;
import com.youpass.model.ReleaseExamInfo;
import com.youpass.model.SetSessionInfo;
import com.youpass.model.UserInfo;
import com.youpass.service.ExamService;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */

@RestController
@RequestMapping(path = "api")
public class ExamController {
    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }


    /**
     * 查看课程的所有考试
     *
     * @param courseId;
     * @return 成功情况
     * {
     * "code": 100,
     * "msg": "成功",
     * "data": [
     * {
     * "exam_id": {
     * "examId": 1,
     * "courseId": 1000
     * },
     * "start_time": null,
     * "end_time": null,
     * "title": "exam1"
     * },
     * {
     * "exam_id": {
     * "examId": 2,
     * "courseId": 1000
     * },
     * "start_time": null,
     * "end_time": null,
     * "title": "exam2"
     * }
     * ]
     * }
     * 失败情况
     * {
     * "code": 500,
     * "msg": "课程不存在",
     * "data": null
     * }
     */
    @GetMapping(path = "courseGetExam/{courseId}")
    public Result<Object> GetExamsByCourseId(@PathVariable("courseId") Long courseId) {
        return examService.GetExamsByCourseId(courseId);
    }

    /**
     * @param id
     * @return 成功情况
     * {
     * "code": 100,
     * "msg": "成功",
     * "data": [
     * {
     * "exam_id": {
     * "examId": 1,
     * "courseId": 1000
     * },
     * "start_time": null,
     * "end_time": null,
     * "title": "exam1"
     * },
     * {
     * "exam_id": {
     * "examId": 2,
     * "courseId": 1000
     * },
     * "start_time": null,
     * "end_time": null,
     * "title": "exam2"
     * }
     * ]
     * }
     */
    @GetMapping(path = "studentGetExam")
    public Result<Object> GetExamsByStudentId(@RequestAttribute(name = "id") Long id) {
        return examService.GetExamByStudentId(id);
    }

    @PostMapping(path = "setSession")
    public Result<Object> SetSession(HttpServletRequest request, @RequestAttribute(name = "id") Long id, @RequestBody SetSessionInfo setSessionInfo) throws ParseException {
        return examService.SetSession(request, id, setSessionInfo);
    }

    @PostMapping(path = "releasetest")
    public Result<Object> ReleaseTest(@RequestAttribute(name = "id") Long id, ReleaseExamInfo releaseExamInfo) {
        return examService.ReleaseTest(id, releaseExamInfo);
    }

    @PostMapping(path = "takeexam/studentPostAnswer")
    public Result<Object> PostAnswer(HttpServletRequest request, @RequestAttribute(name = "id") Long id, @RequestBody PostAnswerInfo postAnswerInfo) {
        return examService.PostAnswer(request, id, postAnswerInfo);
    }

    @DeleteMapping(path = "takeexam/deleteSession")
    public Result<Object> DeleteSession(HttpServletRequest request, @RequestAttribute(name = "id") Long id) {
        return examService.DeleteSession(request, id);
    }

    @GetMapping(path = "takeexam/getExamQuestion")
    public Result<Object> GetExamQuestion(HttpServletRequest request, @RequestAttribute(name = "id") Long id) {
        return examService.GetExamQuestion(request, id);
    }
}

