package com.youpass.controller;

import com.youpass.pojo.Teacher;
import com.youpass.service.TestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/test")
public class TestController {
    public final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("get")
    public String test(){
        // List<Student> studentList = new ArrayList<>();
        // studentList.add(new Student("234","liusyuzhi","ewr","wer"));
        // studentList.add(new Student("235674","lius657yuzhi","e35wr","w546er"));
        // return ResultUtil.success(studentList);
        return "";
    }
    @GetMapping(path = "{teacherId}")
    public void test02(@PathVariable("teacherId") Long  id){
        testService.deleteTeacher(id);
    }
}
