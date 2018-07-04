package com.example.eas.controller;

import com.example.eas.dao.*;
import com.example.eas.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/*教师操作页面中的相关功能实现和页面跳转及后台方法调用*/
@Controller
public class TeacherController {

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    TechCourseDao techCourseDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    CourseStudentDao courseStudentDao;
    @Autowired
    StuCourseDao stuCourseDao;

    /*教师首页*/
    @GetMapping("/tmain/{id}")
    public String teacherMain(@PathVariable("id") Integer id, Model model){
        Teacher teacher = teacherDao.get(id);
        model.addAttribute("teac",teacher);
        Collection<TeacherCourse> teacherCourses = techCourseDao.getAll(teacher);
        model.addAttribute("tCourses",teacherCourses);
        return "teacher/teacherMain";
    }

    //教师信息编辑页面跳转
    @GetMapping("/specteac/{id}")
    public String toSpecTeacEditPage(@PathVariable("id") Integer id,Model model){
        Teacher teacher = teacherDao.get(id);
        model.addAttribute("teac", teacher);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        return "teacher/editPage";
    }

    //教师修改
    @PostMapping("/specTeac")
    public String updateSpecTeacher(Teacher teacher){
        teacherDao.save(teacher);
        return "redirect:/tmain/"+teacher.getId();
    }

    //教师课程退选
    @DeleteMapping("/sepcTeaccoursedel/{cid}/{tid}")
    public String deleteSpecTeacherCourse(@PathVariable("cid") Integer cid, @PathVariable("tid") Integer tid){
        techCourseDao.delete(cid,tid);
        return "redirect:/tmain/"+tid;
    }

    //新开课
    @GetMapping("/teaccourse/{id}")
    public String addCoursePage(Model model, @PathVariable("id") Integer id){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        Collection<Teacher> teachers = teacherDao.getAll();
        model.addAttribute("teachers",teachers);
        Teacher teacher = teacherDao.get(id);
        model.addAttribute("teac",teacher);
        return "teacher/addPage";
    }

    //课程添加
    @PostMapping("/teaccourse/{id}")
    public String addCourse(Course course, @PathVariable("id") Integer id){
        courseDao.save(course);
        return "redirect:/tmain/"+id;
    }

    //查询所有课程返回列表页面
    @GetMapping("/teacCourses/{tid}")
    public String stuCoursesList(@PathVariable("tid") Integer tid, Model model){
        Teacher teacher = teacherDao.get(tid);
        Collection<TeacherCourse> teacherCourses = techCourseDao.getAll(teacher);
        model.addAttribute("courses", teacherCourses);
        model.addAttribute("teac",teacher);
        return "teacher/courseList";
    }

    //课程信息修改
    @GetMapping("/teaccourse/{cid}/{tid}")
    public String eidtCoursePage(@PathVariable("cid") Integer cid, @PathVariable("tid") Integer tid, Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        courseDao.getAll();
        Course course = courseDao.get(cid);
        Collection<Teacher> teachers = teacherDao.getAll();
        Teacher teacher = teacherDao.get(tid);
        model.addAttribute("teac",teacher);
        model.addAttribute("teachers",teachers);
        model.addAttribute("deps",departments);
        model.addAttribute("course",course);

        return "teacher/courseEditPage";
    }

    /*课程信息修改*/
    @PostMapping("/teaccourseedit/{tid}")
    public String editCourse(Course course, @PathVariable("tid") Integer tid){
        //System.out.println(course.toString());
        courseDao.save(course);
        return "redirect:/teacCourses/"+tid;
    }

    //课程详情
    @GetMapping("/teaccoursedetil/{cid}/{tid}")
    public String courseDetilPage(@PathVariable("cid") Integer cid, @PathVariable("tid") Integer tid, Model model){
        courseDao.getAll();
        Course course = courseDao.get(cid);
        model.addAttribute("course",course);
        Collection<CourseStudent> students = courseStudentDao.getAllStudent(course);
        model.addAttribute("students",students);
        Collection<Teacher> teachers = courseStudentDao.getAllTeacher(course);
        model.addAttribute("teachers",teachers);
        Teacher teacher = teacherDao.get(tid);
        model.addAttribute("teac",teacher);
        return "teacher/detailPage";
    }

    //成绩修改
    @GetMapping("/teaccourse/{cid}/{sid}/{grade}/{tid}")
    public String editGrade(CSG csg, @PathVariable("tid") Integer tid, Model model){
        Teacher teacher = teacherDao.get(tid);
        //System.out.println(csg.toString());
        model.addAttribute("csg",csg);
        model.addAttribute("teac",teacher);
        return "teacher/gradePage";
    }

    /*学生成绩修改*/
    @PostMapping("/teaccoursegradeedit/{tid}")
    public String editCourseGrade(CSG csg, @PathVariable("tid") Integer tid){
        //System.out.println(course.toString());
        courseDao.editGrade(csg);
        return "redirect:/teaccoursedetil/"+csg.getCid()+"/"+tid;
    }

    //学生课程退选
    @DeleteMapping("/teacstucoursedel/{cid}/{sid}/{tid}")
    public String deleteSpecStudentCourse(@PathVariable("cid") Integer cid, @PathVariable("sid") Integer sid, @PathVariable("tid") Integer tid){
        stuCourseDao.delete(cid,sid);
        return "redirect:/teaccoursedetil/"+cid+"/"+tid;
    }
}
