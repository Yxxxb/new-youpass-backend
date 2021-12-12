package com.youpass.service;

import com.youpass.model.UserInfo;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface AccountService {
    public Result<Object> SignUp(UserInfo signUpInfo);

    public Result<Object> Login(HttpServletRequest request, UserInfo loginInfo);

    public Result<Object> CheckState(Long id);

    public Result<Object> getIdentity(Long id);

    public Result<Object> getUserInfo(Long id);

    public Result<Object> updateUserInfo(UserInfo userInfo);

    public Result<Object> quitAccount(HttpServletRequest request);

    public Result<Object> uploadImage(Long id, MultipartFile file);

    public void getImage(Long id, HttpServletResponse response);
}
