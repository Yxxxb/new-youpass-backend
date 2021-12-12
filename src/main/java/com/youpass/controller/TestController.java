package com.youpass.controller;

import com.youpass.pojo.Student;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @GetMapping("api/test")
    public String test(HttpServletRequest request){
        var session = request.getSession();
        System.out.println(session.getAttribute("id"));
        return "";
    }
}
