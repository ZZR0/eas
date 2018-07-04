package com.example.eas.controller;

import com.example.eas.entities.Admin;
import com.example.eas.entities.Student;
import com.example.eas.entities.Teacher;
import com.example.eas.mapper.AdminMapper;
import com.example.eas.mapper.StudentMapper;
import com.example.eas.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;

    //登录方法
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        Admin admin = adminMapper.findByNameAndPassword(username,password);
        Teacher teacher = teacherMapper.findByNameAndPassword(username,password);
        Student student = studentMapper.findByNameAndPassword(username,password);
        if (admin != null){
            //管理员登录成功
            session.setAttribute("LoginUser","管理员: "+username);
            return  "redirect:/main.html";
        }else if (teacher != null){
            //教师登录成功
            session.setAttribute("LoginUser","教师: "+teacher.getName());
            return  "redirect:/tmain/"+teacher.getId();
        }else if (student != null){
            //学生登录成功
            session.setAttribute("LoginUser","学生: "+student.getName());
            return  "redirect:/smain/"+student.getId();
        }else{
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
}
