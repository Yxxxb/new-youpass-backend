package com.youpass.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.youpass.util.ReturnType.Result.ResultEnum.USER_NOT_LOGIN;
import static com.youpass.util.SendMsgUtil.sendJsonMessage;


public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object id = request.getSession().getAttribute("id");

        if (id == null) {
            System.out.println("没有登录信息，已拦截");
            sendJsonMessage(response, ResultUtil.error(USER_NOT_LOGIN));
            return false;
        } else {
            System.out.println("放行 id = " + id);
            request.setAttribute("id", id);
            System.out.println(request.getAttribute("id"));
            return true;
        }
    }

}
