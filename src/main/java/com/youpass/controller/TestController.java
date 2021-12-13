package com.youpass.controller;

import com.youpass.dao.CourseRepository;
import com.youpass.dao.TeacherRepository;
import com.youpass.pojo.Student;
import com.youpass.pojo.pk.TeacherId;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    private final TeacherRepository teacherRepository;

    private CourseRepository courseRepository;

    @Autowired
    public TestController(TeacherRepository teacherRepository,
                          CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("api/test")
    public Result<Object> test() {
        var teacher = teacherRepository.findTeacherByName("danny");
        return ResultUtil.success(teacher);
    }

    @GetMapping("api/test2")
    public Result<Object> test2() {
        var course = courseRepository.findCourseByTitle("c1");
        return ResultUtil.success(course);
    }

}
