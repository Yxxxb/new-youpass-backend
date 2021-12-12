package com.youpass.controller;

import com.youpass.model.UserInfo;
import com.youpass.service.AccountService;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "api")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    /**
     * 注册
     *
     * @param userInfo private String name; 姓名
     *                 private String password; 密码
     *                 private String email; 邮箱 唯一标识
     *                 private Integer type; 类型 0老师 1学生
     * @return 成功情况
     * {
     * "code": 100,
     * "msg": "成功",
     * "data": 1950002     //账号
     * }
     * 失败情况
     * {
     * "code": 1,
     * "msg": "邮箱已存在",
     * "data": null
     * }
     */
    @PostMapping(path = "signup")
    public Result<Object> SignUp(@RequestBody UserInfo userInfo) {
        return accountService.SignUp(userInfo);
    }

    @PostMapping(path = "login")
    public Result<Object> Login(HttpServletRequest request, @RequestBody UserInfo userInfo) {
        return accountService.Login(request, userInfo);
    }
}
