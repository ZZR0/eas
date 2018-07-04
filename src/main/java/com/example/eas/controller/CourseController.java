package com.example.eas.controller;

import com.example.eas.dao.*;

import com.example.eas.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/*课程操作有关的所有页面跳转及后台方法调用*/
@Controller
public class CourseController {

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    CourseStudentDao courseStudentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    StuCourseDao stuCourseDao;

    //查询所有课程返回列表页面
    @GetMapping("/courses")
    public String coursesList(Model model){
        Collection<Course> courses = courseDao.getAll();
        model.addAttribute("courses", courses);
        return "course/list";
    }

    /*加课页面跳转*/
    @GetMapping("/course")
    public String addCoursePage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        Collection<Teacher> teachers = teacherDao.getAll();
        model.addAttribute("teachers",teachers);
        return "course/addPage";
    }

    //课程添加
    @PostMapping("/course")
    public String addCourse(Course course){
        courseDao.save(course);
        return "redirect:/courses";
    }

    //课程详情
    @GetMapping("/coursedetil/{id}")
    public String courseDetilPage(@PathVariable("id") Integer id, Model model){
        courseDao.getAll();
        Course course = courseDao.get(id);
        model.addAttribute("course",course);
        Collection<CourseStudent> students = courseStudentDao.getAllStudent(course);
        model.addAttribute("students",students);
        Collection<Teacher> teachers = courseStudentDao.getAllTeacher(course);
        model.addAttribute("teachers",teachers);
        return "course/detailPage";
    }
    //课程信息修改页面跳转
    @GetMapping("/course/{id}")
    public String eidtCoursePage(@PathVariable("id") Integer id, Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        courseDao.getAll();
        Course course = courseDao.get(id);
        Collection<Teacher> teachers = teacherDao.getAll();
        model.addAttribute("teachers",teachers);
        model.addAttribute("deps",departments);
        model.addAttribute("course",course);

        return "course/editPage";
    }

    //课程信息修改结果保存
    @PostMapping("/courseedit")
    public String editCourse(Course course){
        //System.out.println(course.toString());
        courseDao.save(course);
        return "redirect:/courses";
    }

    //课程删除
    @DeleteMapping("/course/{id}")
    public String deleteCourse(@PathVariable("id") Integer id){
        courseDao.delete(id);
        return "redirect:/courses";
    }

    //课程删除
    @DeleteMapping("/course/{cid}/{sid}")
    public String deleteCourse(@PathVariable("cid") Integer cid, @PathVariable("sid") Integer sid){
        stuCourseDao.delete(cid,sid);
        return "redirect:/coursedetil/"+cid;
    }

    //成绩修改页面跳转
    @GetMapping("/course/{cid}/{sid}/{grade}")
    public String editGrade(CSG csg, Model model){
        //System.out.println(csg.toString());
        model.addAttribute("csg",csg);
        return "course/gradePage";
    }

    /*成绩修改*/
    @PostMapping("/coursegradeedit")
    public String editCourseGrade(CSG csg){
        //System.out.println(course.toString());
        courseDao.editGrade(csg);
        return "redirect:/coursedetil/"+csg.getCid();
    }
}
