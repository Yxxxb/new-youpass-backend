package com.youpass.controller;

import com.youpass.model.UserInfo;
import com.youpass.service.AccountService;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;

@RestController
@RequestMapping(path = "api/account")
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
     * * {
     * * "code": 100,
     * * "msg": "成功",
     * * "data": 1950002     //账号
     * * }
     * * 失败情况
     * * {
     * * "code": 1,
     * * "msg": "邮箱已存在",
     * * "data": null
     * * }
     */
    @PostMapping(path = "signup")
    public Result<Object> SignUp(@RequestBody UserInfo userInfo) {
        return accountService.SignUp(userInfo);
    }

    /**
     * 登录
     *
     * @param request  用来获取session
     * @param userInfo id 账号
     *                 password 密码
     * @return 成功
     * {
     * "code": 100,
     * "msg": "成功",
     * "data": null
     * }
     */
    @PostMapping(path = "login")
    public Result<Object> Login(HttpServletRequest request, @RequestBody UserInfo userInfo) {
        return accountService.Login(request, userInfo);
    }

    /**
     * 验证是否登录（验证session）
     *
     * @param id 用户id
     * @return {
     * "code": 100,
     * "msg": "成功",
     * "data": null
     * }
     */
    @GetMapping(path = "checkState")
    public Result<Object> CheckState(@RequestAttribute(name = "id") Long id) {
        return accountService.CheckState(id);
    }

    /**
     * 获取这个用户是老师还是学生
     *
     * @param id
     * @return 学生
     * {
     * "code": 100,
     * "msg": "成功",
     * "data": 1        [学生data值是1]
     * }
     * 老师
     * {
     * "code": 100,
     * "msg": "成功",
     * "data": 0        [老师data是0]
     * }
     */
    @GetMapping(path = "getIdentity")
    public Result<Object> GetIdentity(@RequestAttribute(name = "id") Long id) {
        return accountService.getIdentity(id);
    }

    /**
     * 获得用户的基本信息
     *
     * @param id [通过session获得的id]
     * @return {
     * "code": 100,
     * "msg": "成功",
     * "data": {
     * "name": "danny1",
     * "id": null,
     * "password": null,
     * "email": null,
     * "type": 1,
     * "location": null
     * }
     * }
     */
    @GetMapping(path = "getUserInfo")
    public Result<Object> GetUserInfo(@RequestAttribute(name = "id") Long id) {
        return accountService.getUserInfo(id);
    }

    /**
     * @param id       [通过session获得的id]
     * @param userInfo [下列是必要信息]
     *                 private String name;
     *                 private String email;
     *                 private String location;
     * @return {
     * "code": 100,
     * "msg": "成功",
     * "data": null
     * }
     */
    @PutMapping(path = "updateUserInfo")
    public Result<Object> UpdateUserInfo(@RequestAttribute(name = "id") Long id, @RequestBody UserInfo userInfo) {
        userInfo.setId(id);
        return accountService.updateUserInfo(userInfo);
    }

    /**
     * 退出登录
     *
     * @param request 用来删除session
     * @return {
     * "code": 100,
     * "msg": "成功",
     * "data": null
     * }
     */
    @DeleteMapping(path = "quitAccount")
    public Result<Object> QuitAccount(HttpServletRequest request) {
        return accountService.quitAccount(request);
    }

    /**
     * 上传头像
     *
     * @param id   通过session获得的id
     * @param file 在form-data中上传的file，并且key为'file',value为对应的file
     * @return
     */
    @PutMapping(path = "uploadImage")
    public Result<Object> UploadImage(@RequestAttribute(name = "id") Long id, @RequestParam("file") MultipartFile file) {
        return accountService.uploadImage(id, file);
    }

    /**
     * 获得头像
     * @param response
     * @param id
     */
    @GetMapping(path = "getImage")
    public void getImage(HttpServletResponse response, @RequestAttribute(name = "id") Long id) {
        accountService.getImage(id, response);
    }


}
