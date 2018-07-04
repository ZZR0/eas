package com.example.eas.controller;

import com.example.eas.dao.*;
import com.example.eas.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/*学生操作页面中的相关功能实现和页面跳转及后台方法调用*/
@Controller
public class StudentController {

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    TechCourseDao techCourseDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    StuCourseDao stuCourseDao;
    @Autowired
    CourseDao courseDao;

    /*学生首页*/
    @GetMapping("/smain/{id}")
    public String studentMain(@PathVariable("id") Integer id, Model model){
        Student student = studentDao.get(id);
        model.addAttribute("stu",student);
        Collection<StuCourse> stuCourses = stuCourseDao.getAll(student);
        model.addAttribute("sCourses",stuCourses);
        return "student/studentMain";
    }

    //学生信息编辑页面跳转
    @GetMapping("/specstu/{id}")
    public String toSpecStuEditPage(@PathVariable("id") Integer id,Model model){
        Student student = studentDao.get(id);
        model.addAttribute("stu", student);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        return "student/editPage";
    }

    //学生修改
    @PostMapping("/specStu")
    public String updateSpecStudent(Student student){
        studentDao.save(student);
        return "redirect:/smain/"+student.getId();
    }

    //学生加课页面跳转
    @GetMapping("/sepcStuCourse/{id}")
    public String toSpecStuAddCoursePage(@PathVariable("id") Integer id,Model model){
        Student student = studentDao.get(id);
        model.addAttribute("stu", student);
        Collection<Course> courses = courseDao.getAll();
        Map<Integer,StuCourse> stuCourseMap = stuCourseDao.getAllMap(student);
        Iterator<Course> iterator = courses.iterator();
        while(iterator.hasNext()){
            Course course = iterator.next();
            if (stuCourseMap.containsKey(course.getId())) iterator.remove();
        }
        //System.out.println(courses.toString());
        model.addAttribute("courses", courses);
        return "student/addCoursePage";
    }

    //学生课程添加
    @GetMapping("/sepcStuaddcourse/{cid}/{sid}")
    public String sepcStuAddCourse(@PathVariable("cid") Integer cid,@PathVariable("sid") Integer sid, Map<String,Object> map){
        int i = stuCourseDao.save(cid, sid);
        if (i!=0) map.put("msg","课程不允许重复选择");
        return "redirect:/smain/"+sid;
    }

    //学生课程退选
    @DeleteMapping("/sepcStucoursedel/{cid}/{sid}")
    public String deleteSpecStudentCourse(@PathVariable("cid") Integer cid, @PathVariable("sid") Integer sid){
        stuCourseDao.delete(cid,sid);
        return "redirect:/smain/"+sid;
    }

    //查询所有课程返回列表页面
    @GetMapping("/stuCourses/{sid}")
    public String stuCoursesList(@PathVariable("sid") Integer sid, Model model){
        Student student = studentDao.get(sid);
        Collection<StuCourse> stuCourses = stuCourseDao.getAll(student);
        model.addAttribute("courses", stuCourses);
        model.addAttribute("stu",student);
        return "student/courseList";
    }
}
