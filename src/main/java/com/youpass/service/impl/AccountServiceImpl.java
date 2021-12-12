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
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public Result<Object> CheckState(Long id) {
        //因为有拦截器 所以到了这里就一定可以
        return ResultUtil.success();
    }

    @Override
    public Result<Object> getIdentity(Long id) {
        if (teacherRepository.existsById(new TeacherId(id))) {
            return ResultUtil.success(0);
        } else {
            return ResultUtil.success(1);
        }
    }

    @Override
    public Result<Object> getUserInfo(Long id) {
        return null;
    }


}
