package com.youpass.controller;

import com.youpass.pojo.Student;
import com.youpass.pojo.Teacher;
import com.youpass.service.AccountService;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping(path = "student/signup")
    public Result<Object> stuSignUp(@RequestBody Student student){
        return accountService.stuSignUp(student);
    }
    @PostMapping(path = "teacher/signup")
    public Result<Object> stuSignUp(@RequestBody Teacher teacher){
        return accountService.teacherSignUp(teacher);
    }
}
