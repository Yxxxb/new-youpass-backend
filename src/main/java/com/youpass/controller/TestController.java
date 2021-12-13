package com.youpass.controller;

import com.youpass.pojo.Student;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
    @GetMapping("api/test")

    public void test(HttpServletResponse response) throws IOException {

        Long id = 1950000L;

        String workingDirectory = System.getProperty("user.dir");
        String imgDirectory = workingDirectory + "\\Img";
        String imgPath = imgDirectory + "\\" + id.toString() + ".jpg";
        if (!new File(imgPath).exists()) {
            imgPath = imgDirectory + "\\default.jpg";
        }
        System.out.println(imgPath);
        File file = new File(imgPath);
        InputStream targetStream = new FileInputStream(file);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(targetStream, response.getOutputStream());
    }
}
