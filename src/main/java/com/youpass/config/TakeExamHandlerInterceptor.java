package com.youpass.config;

import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.youpass.util.ReturnType.Result.ResultEnum.WITHOUT_AUTHORITY;
import static com.youpass.util.SendMsgUtil.sendJsonMessage;

/**
 * @author: 叶栩冰
 * @number: 1953348
 * @indicate: JDK 11.0.12
 */
public class TakeExamHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object id = request.getSession().getAttribute("id");
        Object course_id = request.getSession().getAttribute("course_id");
        Object exam_id = request.getSession().getAttribute("exam_id");

        System.out.println(course_id);
        System.out.println(exam_id);

        if (course_id == null || exam_id == null) {
            System.out.println("没有考试权限，已拦截");
            sendJsonMessage(response, ResultUtil.error(WITHOUT_AUTHORITY));
            return false;
        } else {
            System.out.println("放行 id = " + id);
            System.out.println("放行 course_id = " + course_id);
            System.out.println("放行 exam_id = " + exam_id);
            request.setAttribute("course_id", course_id);
            request.setAttribute("exam_id", exam_id);
            return true;
        }
    }
}
