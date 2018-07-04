package com.example.eas.dao;

import com.example.eas.entities.TeacherCourse;
import com.example.eas.entities.Teacher;
import com.example.eas.mapper.CourseMapper;
import com.example.eas.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*课程后台管理类，用于从数据库中获取教师课程信息*/
@Repository
public class TechCourseDao {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    CourseDao courseDao;

    private static Map<Integer, TeacherCourse> teacherCourseMap = new HashMap<Integer, TeacherCourse>();

    private static List<TeacherCourse> teacherCourses = null;


    /*public int save(String cid, Integer sid){
        int i = teacherMapper.selectCount(cid, sid);
        if (i==0) teacherMapper.addCourse(cid,sid);
        return i;
    }*/

    public Collection<TeacherCourse> getAll(Teacher teacher){
        teacherCourseMap.clear();
        teacherCourses = teacherMapper.selectTeacherCoures(teacher);
        departmentDao.getDepartments();
        courseDao.getAll();
        for (TeacherCourse teacherCourse:teacherCourses) {
            teacherCourse.setDepartmentName(departmentDao.getDepartment(teacherCourse.getDepartmentId()));
            teacherCourse.setName(courseDao.get(teacherCourse.getId()).getName());
            teacherCourse.setCount(courseDao.get(teacherCourse.getId()).getCount());
            teacherCourseMap.put(teacherCourse.getId(),teacherCourse);
        }
        //System.out.println(teacherCourseMap.toString());
        return teacherCourseMap.values();
    }

    public TeacherCourse get(Integer id){
        return teacherCourseMap.get(id);
    }

    public void delete(Integer cid, Integer tid){
        int c = teacherMapper.checkCourse(cid);
        if (c==1) courseMapper.deleteCourse(cid);
        else teacherMapper.deleteTeacherCourse(cid,tid);
    }
}
