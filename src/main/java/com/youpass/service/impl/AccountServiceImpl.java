package com.youpass.service.impl;

import com.youpass.dao.StudentRepository;
import com.youpass.dao.TeacherRepository;
import com.youpass.model.UserInfo;
import com.youpass.pojo.Student;
import com.youpass.pojo.Teacher;
import com.youpass.pojo.pk.StudentId;
import com.youpass.pojo.pk.TeacherId;
import com.youpass.service.AccountService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultEnum;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.youpass.util.ReturnType.Result.ResultEnum.File_ERROR;
import static com.youpass.util.ReturnType.Result.ResultEnum.USER_NOT_LOGIN;

@Service
public class AccountServiceImpl implements AccountService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public AccountServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    @Transactional
    public Result<Object> SignUp(UserInfo signUpInfo) {
        /*错误处理*/
        if (signUpInfo.getPassword() == null
                || signUpInfo.getName() == null
                || signUpInfo.getEmail() == null
                || signUpInfo.getType() == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }

        if (signUpInfo.getType() == 1) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(signUpInfo.getEmail());
            if (studentOptional.isPresent()) {
                return ResultUtil.error(ResultEnum.EMAIL_IS_EXISTS);
            } else {
                var id = studentRepository.getNextId().orElse(studentRepository.minId);
                var student = Student.Builder()
                        .setId(new StudentId(id))
                        .setName(signUpInfo.getName())
                        .setPassword(signUpInfo.getPassword())
                        .setEmail(signUpInfo.getEmail())
                        .build();
                studentRepository.save(student);
                return ResultUtil.success(id);
            }
        } else if (signUpInfo.getType() == 0) {
            Optional<Teacher> teacherOptional = teacherRepository.findTeacherByEmail(signUpInfo.getEmail());
            if (teacherOptional.isPresent()) {
                return ResultUtil.error(ResultEnum.EMAIL_IS_EXISTS);
            } else {
                var id = teacherRepository.getNextId().orElse(teacherRepository.minId);
                var teacher = Teacher.Builder()
                        .setId(new TeacherId(id))
                        .setName(signUpInfo.getName())
                        .setPassword(signUpInfo.getPassword())
                        .setEmail(signUpInfo.getEmail())
                        .build();
                teacherRepository.save(teacher);
                return ResultUtil.success(id);
            }
        } else {
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    @Transactional
    public Result<Object> Login(HttpServletRequest request, UserInfo loginInfo) {
        if (loginInfo.getId() == null || loginInfo.getPassword() == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        if (studentRepository.existsById(new StudentId(loginInfo.getId()))) {
            var student = studentRepository.findById(new StudentId(loginInfo.getId())).get();
            if (student.getPassword().equals(loginInfo.getPassword())) {
                //如果相同
                HttpSession session = request.getSession(true);
                session.setAttribute("id", loginInfo.getId());
                return ResultUtil.success();
            } else {
                return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
            }
        } else if (teacherRepository.existsById(new TeacherId(loginInfo.getId()))) {
            var teacher = teacherRepository.findById(new TeacherId(loginInfo.getId())).get();
            if (teacher.getPassword().equals(loginInfo.getPassword())) {
                //如果相同
                HttpSession session = request.getSession(true);
                session.setAttribute("id", loginInfo.getId());
                return ResultUtil.success();
            } else {
                return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
            }
        } else {
            return ResultUtil.error(ResultEnum.USER_MISS);
        }
    }

    @Override
    @Transactional
    public Result<Object> CheckState(Long id) {
        //因为有拦截器 所以到了这里就一定可以
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result<Object> getIdentity(Long id) {
        if (teacherRepository.existsById(new TeacherId(id))) {
            return ResultUtil.success(0);
        } else {
            return ResultUtil.success(1);
        }
    }

    @Override
    @Transactional
    public Result<Object> getUserInfo(Long id) {
        if (studentRepository.existsById(new StudentId(id))) {
            var student = studentRepository.findById(new StudentId(id)).get();
            var userInfo = new UserInfo();
            userInfo.setEmail(student.getEmail());
            userInfo.setName(student.getName());
            userInfo.setLocation(student.getLocation());
            userInfo.setType(1);
            return ResultUtil.success(userInfo);
        } else if (teacherRepository.existsById(new TeacherId(id))) {
            var teacher = teacherRepository.findById(new TeacherId(id)).get();
            var userInfo = new UserInfo();
            userInfo.setEmail(teacher.getEmail());
            userInfo.setName(teacher.getName());
            userInfo.setLocation(teacher.getLocation());
            userInfo.setType(0);
            return ResultUtil.success(userInfo);
        } else {
            return ResultUtil.error(ResultEnum.USER_MISS);
        }
    }

    @Override
    @Transactional
    public Result<Object> updateUserInfo(UserInfo userInfo) {
        if (userInfo.getId() == null
                || userInfo.getLocation() == null
                || userInfo.getEmail() == null
                || userInfo.getName() == null) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        if (studentRepository.existsById(new StudentId(userInfo.getId()))) {
            var student = studentRepository.findById(new StudentId(userInfo.getId())).get();
            student.setName(userInfo.getName());
            student.setEmail(userInfo.getEmail());
            student.setLocation(userInfo.getLocation());
            studentRepository.save(student);
            return ResultUtil.success();
        } else if (teacherRepository.existsById(new TeacherId(userInfo.getId()))) {
            var teacher = teacherRepository.findById(new TeacherId(userInfo.getId())).get();
            teacher.setName(userInfo.getName());
            teacher.setEmail(userInfo.getEmail());
            teacher.setLocation(userInfo.getLocation());
            teacherRepository.save(teacher);
            return ResultUtil.success();
        } else {
            return ResultUtil.error(ResultEnum.USER_MISS);
        }
    }

    @Override
    @Transactional
    public Result<Object> quitAccount(HttpServletRequest request) {
        if (request.getSession().getAttribute("id") == null) {
            return ResultUtil.error(USER_NOT_LOGIN);
        } else {
            request.getSession().removeAttribute("id");
            return ResultUtil.success();
        }
    }

    @Override
    @Transactional
    public Result<Object> uploadImage(Long id, MultipartFile file) {
        if (file.isEmpty()) {
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        try {
            byte[] bytes = file.getBytes();
            String workingDirectory = System.getProperty("user.dir");
            String imgDirectory = workingDirectory + "\\Img";
            File imgFolder = new File(imgDirectory);
            if (!imgFolder.exists() && !imgFolder.isDirectory()) {
                imgFolder.mkdirs();
            }
            Path path = Paths.get(imgDirectory + "\\" + id.toString() + ".jpg");
            Files.write(path, bytes);
            return ResultUtil.success();
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error(File_ERROR);
        }
    }

    @Override
    @Transactional
    public void getImage(Long id, HttpServletResponse response) {
        String workingDirectory = System.getProperty("user.dir");
        String imgDirectory = workingDirectory + "\\Img";
        String imgPath = imgDirectory + "\\" + id.toString() + ".jpg";
        if (!new File(imgPath).exists()) {
            imgPath = imgDirectory + "\\default.jpg";
        }
        File file = new File(imgPath);

        try {
            InputStream targetStream = new FileInputStream(file);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(targetStream, response.getOutputStream());
        }
        catch (IOException e){
            System.out.println("文件出现问题");
        }
    }
}
