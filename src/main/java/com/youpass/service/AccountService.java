package com.youpass.service;

import com.youpass.model.UserInfo;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface AccountService {
    public Result<Object> SignUp(UserInfo signUpInfo);
    public Result<Object> Login(HttpServletRequest request, UserInfo loginInfo);
    // public Result<Object> teacherSignUp(Teacher teacher);
}
