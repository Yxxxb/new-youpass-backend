package com.youpass.controller;

import com.youpass.model.QuestionInfo;
import com.youpass.service.ScoreService;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/score")
public class ScoreController {

    private final ScoreService scoreService;


    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    /**
     * 计算某门课程的某门考试的所有学生总成绩
     * @param stuInfo
     *     private Long questionId;
     *     private Long courseId;
     *     private Long examId;
     * @return
           成功情况
     *     {
     *     "code": 100,
     *     "msg": "成功",
     *     "data": 该门考试成绩录入成功！
     *     }
     */
    @PostMapping(path = "CalStuScore")
    public Result<Object> calStuScore(@RequestBody QuestionInfo stuInfo) {
        return scoreService.calStuScore(stuInfo);
    }

    /**
     * 查看某门考试所有学生的分数
     * @param courseId 课程id
     * @param examId 考试id
     * @return
           成功情况
     *     {
     *     "code": 100,
     *     "msg": "成功",
     *     "data": List<ScoreInfo>{
     *             class ScoreInfo:{
     *                 private Long studentId;
     *                 private String name;
     *                 private Integer score;
     *                 }
     *             }
     *     }
     */
    @GetMapping(path = "getGrade{courseId}/{examId}")
    public Result<Object> getGrade(@PathVariable Long courseId, @PathVariable Long examId) {
        return scoreService.getGrade(courseId, examId);
    }

    /**
     * 自动批改某门课程某场考试某道题的所有解答
     * @param questionInfo
     *     private Long questionId;
     *     private Long courseId;
     *     private Long examId;
     * @return
           成功情况
     *     {
     *     "code": 100,
     *     "msg": "成功",
     *     "data": 该题自动批改完成！
     *     }
     */
    @PostMapping(path = "autoCorrect")
    public Result<Object> autoCorrect(@RequestBody QuestionInfo questionInfo){
        return scoreService.autoCorrect(questionInfo);
    }

    /**
     * 自动批改某门课程某场考试某道题的所有解答
     * @param questionInfo
     *     private Long questionId;
     *     private Long courseId;
     *     private Long examId;
     *     private List<ScoreInfo>
     * @return
    成功情况
     *     {
     *     "code": 100,
     *     "msg": "成功",
     *     "data": 该题手动批改完成！
     *     }
     */
    @PostMapping(path = "manualCorrect")
    public Result<Object> manualCorrect(@RequestBody QuestionInfo questionInfo) {
        return scoreService.manualCorrect(questionInfo);
    }

    /**
     * 查找某个学生某门课程的所有考试的成绩
     * @param id 学生的id
     * @param courseId 课程的id
     * @return Set<CourseExamInfoReturn>
     *     class CourseExamInfoReturn{
     *         private Long courseId;
     *         private Long examId;
     *         private String title;
     *         private Integer score;
     *         }
     */
    @GetMapping(path = "getStuScore/{courseId}")
    public Result<Object> getStuScore(@RequestAttribute(name = "id") Long id, @PathVariable Long courseId){
        return scoreService.getStuScore(id,courseId);
    }
}
