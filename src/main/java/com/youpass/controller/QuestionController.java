package com.youpass.controller;


import com.youpass.model.QuestionInfo;
import com.youpass.service.QuestionService;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "api/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    /**
     * 老师将题目上传到题库中
     * @param questionInfoList 题目的详细信息
     *     private  Long questionId;
     *     private  Long courseId;
     *     private String description;
     *     private Integer type;
     *     private String standardAnswer;
     *     private String subject;
     *     private Date createTime;
     *     private List<OptionInfo> optionInfoList;
     * @return
     *    成功情况
     *    {
     *    "code": 100,
     *    "msg": "成功",
     *    "data": 上传题目成功
     *    }
     *
     */
    @PostMapping(path = "update")
    public Result<Object> uploadQuestion( @RequestBody List<QuestionInfo> questionInfoList) {
//        System.out.println(questionInfoList);
      return questionService.uploadQuestion(questionInfoList);
    }

    /**
     * 根据传入的相关题目信息获取在考试中考了这道题但是还没有被批改的学生的信息和这道题目的相关信息
     * @param questionInfo 传入的信息
     *     private Long questionId;
     *     private Long courseId;
     *     private Long examId;
     * @return
     *     成功情况
     *     {
     *     "code": 100,
     *     "msg": "成功",
     *     "data": class QuestionStuReturn{
     *             Set<StudentExamPaperInfo> studentList;
     *             QuestionInfo questionInfo;
     *             }
     *     }
     */
    @PostMapping(path = "getStu")
    public Result<Object> getStudentDoingQuestion( @RequestBody QuestionInfo questionInfo){
        return questionService.getStudentDoingQuestion(questionInfo);
    }

    @GetMapping(path="getUnmarkedQuestion")
    public Result<Object> getUnmarkedQuestion(@RequestParam(name = "courseId")Long courseId,@RequestParam(name = "examId")Long examId){
        return questionService.getUnmarkedQuestion(courseId,examId);
    }


}
