package com.youpass.controller;

import com.youpass.pojo.Student;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @GetMapping("api/test")
    public String test(){
        // List<Student> studentList = new ArrayList<>();
        // studentList.add(new Student("234","liusyuzhi","ewr","wer"));
        // studentList.add(new Student("235674","lius657yuzhi","e35wr","w546er"));
        // return ResultUtil.success(studentList);
        return "";
    }
}
