package com.example.eas.controller;

import com.example.eas.dao.*;
import com.example.eas.entities.*;
import com.example.eas.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

//管理员操作中的所有页面跳转方法
@Controller
public class AdminController {
    @Autowired
    StudentDao studentDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    StuCourseDao stuCourseDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    TechCourseDao techCourseDao;

    //查询所有学生返回列表页面
    @GetMapping("/stus")
    public String list(Model model){
        Collection<Student> students = studentDao.getAll();
        model.addAttribute("studs", students);
        return "stu/list";
    }

    //学生添加界面处理
    @GetMapping("/stu")
    public String toStuAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        return "stu/addPage";
    }

    //学生添加
    @PostMapping("/stu")
    public String stuAdding(Student student){
        /*System.out.println("学生信息");
        System.out.println(student);*/
        studentDao.save(student);
        return "redirect:/stus";
    }

    //学生信息编辑页面跳转
    @GetMapping("/stu/{id}")
    public String toStuEditPage(@PathVariable("id") Integer id,Model model){
        Student student = studentDao.get(id);
        model.addAttribute("stu", student);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        return "stu/editPage";
    }

    //学生修改
    @PutMapping("/stu")
    public String updateStudent(Student student){
        studentDao.save(student);
        return "redirect:/stus";
    }

    //学生删除
    @DeleteMapping("/stu/{id}")
    public String deleteStudent(@PathVariable("id") Integer id){
        studentDao.delete(id);
        return "redirect:/stus";
    }

    //学生详细信息编辑页面跳转
    @GetMapping("/studetail/{id}")
    public String toStuDetialPage(@PathVariable("id") Integer id,Model model){
        Student student = studentDao.get(id);
        model.addAttribute("stu", student);
        Collection<StuCourse> stuCourses = stuCourseDao.getAll(student);
        //System.out.println(stuCourses.toString());
        model.addAttribute("sCourses",stuCourses);
        return "stu/detailPage";
    }

    //学生加课页面跳转
    @GetMapping("/stuCourse/{id}")
    public String toStuAddCoursePage(@PathVariable("id") Integer id,Model model){
        Student student = studentDao.get(id);
        model.addAttribute("stu", student);
        Collection<Course> courses = courseDao.getAll();
        //System.out.println(courses.toString());
        model.addAttribute("courses", courses);
        return "stu/addCoursePage";
    }

    //学生课程添加
    @GetMapping("/stuaddcourse/{cid}/{sid}")
    public String stuAddCourse(@PathVariable("cid") Integer cid,@PathVariable("sid") Integer sid, Map<String,Object> map){
        int i = stuCourseDao.save(cid, sid);
        if (i!=0) map.put("msg","课程不允许重复选择");
        return "redirect:/studetail/{sid}";
    }

    //学生课程退选
    @DeleteMapping("/coursedel/{cid}/{sid}")
    public String deleteStudentCourse(@PathVariable("cid") Integer cid, @PathVariable("sid") Integer sid){
        stuCourseDao.delete(cid,sid);
        return "redirect:/studetail/{sid}";
    }

    //查询所有教师返回列表页面
    @GetMapping("/techs")
    public String teachList(Model model){
        Collection<Teacher> teachers = teacherDao.getAll();
        model.addAttribute("techs", teachers);
        return "tech/list";
    }

    //教师添加页面
    @GetMapping("/tech")
    public String addTechPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        return "tech/addPage";
    }

    /*教师添加*/
    @PostMapping("/tech")
    public String addTech(Teacher teacher){
        teacherDao.save(teacher);
        return "redirect:/techs";
    }

    /*教师信息修改页面跳转*/
    @GetMapping("/tech/{id}")
    public String eidtTechPage(@PathVariable("id") Integer id, Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        Teacher teacher = teacherDao.get(id);
        model.addAttribute("deps",departments);
        model.addAttribute("tech",teacher);

        return "tech/editPage";
    }

    /*教师信息修改*/
    @PutMapping("/tech")
    public String editTech(Teacher teacher){
        teacherDao.save(teacher);
        return "redirect:/techs";
    }

    /*教师删除*/
    @DeleteMapping("/tech/{id}")
    public String deleteTecher(@PathVariable("id") Integer id){
        teacherDao.delete(id);
        return "redirect:/techs";
    }

    /*教师相信信息页面*/
    @GetMapping("/techdetail/{id}")
    public String toTeacherDetialPage(@PathVariable("id") Integer id,Model model){
        Teacher teacher = teacherDao.get(id);
        model.addAttribute("teacher", teacher);
        Collection<TeacherCourse> teacherCourses = techCourseDao.getAll(teacher);
        //System.out.println(stuCourses.toString());
        model.addAttribute("tCourses",teacherCourses);
        return "tech/detailPage";
    }

    //教師课程退选
    @DeleteMapping("/tcoursedel/{cid}/{sid}")
    public String deleteTeacherCourse(@PathVariable("cid") Integer cid, @PathVariable("sid") Integer sid){
        techCourseDao.delete(cid,sid);
        return "redirect:/techdetail/{sid}";
    }
}
