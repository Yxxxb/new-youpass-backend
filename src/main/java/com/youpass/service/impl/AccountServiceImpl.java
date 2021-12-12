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
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public Result<Object> SignUp(UserInfo signUpInfo) {
        /*错误处理*/
        if(signUpInfo.getPassword()==null
                ||signUpInfo.getName()==null
                ||signUpInfo.getEmail()==null
                ||signUpInfo.getType()==null){
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
        } else if (signUpInfo.getType() == 0){
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
        }else{
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public Result<Object> Login(HttpServletRequest request,UserInfo loginInfo) {
        if (loginInfo.getId()==null||loginInfo.getPassword()==null){
            return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
        }
        if(studentRepository.existsById(new StudentId(loginInfo.getId()))){
            var student = studentRepository.findById(new StudentId(loginInfo.getId())).get();
            if(student.getPassword().equals(loginInfo.getPassword())){
                //如果相同
                HttpSession session = request.getSession(true);
                session.setAttribute("id",loginInfo.getId());
                return ResultUtil.success();
            }else{
                return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
            }
        }else if(teacherRepository.existsById(new TeacherId(loginInfo.getId()))){
            var teacher = teacherRepository.findById(new TeacherId(loginInfo.getId())).get();
            if(teacher.getPassword().equals(loginInfo.getPassword())){
                //如果相同
                HttpSession session = request.getSession(true);
                session.setAttribute("id",loginInfo.getId());
                return ResultUtil.success();
            }else{
                return ResultUtil.error(ResultEnum.INFO_DEFICIENCY);
            }
        }else{
            return ResultUtil.error(ResultEnum.USER_MISS);
        }
    }

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public AccountServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    // /**
    //  * 该方法供Controller层使用，完成学生注册的业务逻辑
    //  * @param student Student类的对象
    //  * @return 若注册成功，返回新生成的id，若失败，返回失败相关信息
    //  */
    // @Override
    // public Result<Object> stuSignUp(Student student) {
    //     Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    //     if (studentOptional.isPresent()) {
    //         String msg="Email has been taken!";
    //         return ResultUtil.error(1,msg);
    //     }
    //     studentRepository.save(student);
    //
    //     //此处的返回内容等于前端交互时再具体决定修改
    //     Map<String,Long> resultMap= new HashMap<>();
    //     resultMap.put("产生的新学号",student.getId());
    //
    //     return ResultUtil.success(resultMap);
    // }
    // /**
    //  * 该方法供Controller层使用，完成老师注册的业务逻辑
    //  * @param teacher Teacher类的对象
    //  * @return 若注册成功，返回新生成的id，若失败，返回失败相关信息
    //  */
    // @Override
    // public Result<Object> teacherSignUp(Teacher teacher) {
    //
    //     Optional<Teacher> teacherOptional = teacherRepository.findTeacherByEmail(teacher.getEmail());
    //     if (teacherOptional.isPresent()) {
    //         String msg="Email has been taken!";
    //         return ResultUtil.error(1,msg);
    //     }
    //     teacherRepository.save(teacher);
    //
    //     //此处的返回内容等于前端交互时再具体决定修改
    //     Map<String,Long> resultMap= new HashMap<>();
    //     resultMap.put("产生的新工号",teacher.getId().getId());
    //
    //     return ResultUtil.success(resultMap);
    // }


}
