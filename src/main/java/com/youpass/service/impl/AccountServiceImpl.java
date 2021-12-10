package com.youpass.service.impl;

import com.youpass.dao.StudentRepository;
import com.youpass.dao.TeacherRepository;
import com.youpass.pojo.Student;
import com.youpass.pojo.Teacher;
import com.youpass.service.AccountService;
import com.youpass.util.ReturnType.Result.Result;
import com.youpass.util.ReturnType.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    //
    // private final StudentRepository studentRepository;
    // private final TeacherRepository teacherRepository;
    // @Autowired
    // public AccountServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository) {
    //     this.studentRepository = studentRepository;
    //     this.teacherRepository = teacherRepository;
    // }

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
