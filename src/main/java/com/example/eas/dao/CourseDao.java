package com.example.eas.dao;

import com.example.eas.entities.*;
import com.example.eas.mapper.CourseMapper;
import com.example.eas.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//课程后台管理类，用于从数据库中获取课程信息
@Repository
public class CourseDao {


    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private CourseMapper courseMapper;

    private static Map<Integer,Course> courseMap = new HashMap<Integer, Course>();

    private static List<Course> courses = null;

    /*
    * 把课程信息更新到数据库中
    * */
    public void save(Course course){
        //System.out.println(course.toString());
        if (courseMap.get(course.getId())==null){
            courseMapper.addCourse(course);
            Integer cid = courseMapper.getCourseId(course);
            for (Integer tid:course.getTeacher()) {
                courseMapper.addCourseTeacher(cid,tid);
            }
        }else{
            Integer cid = course.getId();
            courseMapper.clearCourseTeacher(cid);
            courseMapper.editCourse(course);
            for (Integer tid:course.getTeacher()) {
                courseMapper.addCourseTeacher(cid,tid);
            }
        }
    }

    /*修改成绩*/
    public void editGrade(CSG csg){
        courseMapper.editGrade(csg);
    }

    /*获取所有课程*/
    public Collection<Course> getAll(){
        courseMap.clear();
        courses = courseMapper.selectAllCourse();
        departmentDao.getDepartments();
        for (Course course:courses) {
            course.setDepartmentName(departmentDao.getDepartment(course.getDepartmentId()));
            if (courseMap.get(course.getId())==null) {
                course.setCount(courseMapper.countStudent(course));
                courseMap.put(course.getId(), course);
            }else {
                if (!courseMap.get(course.getId()).getName().contains(course.getName())){
                    courseMap.get(course.getId()).setName(course.getName()+", "+courseMap.get(course.getId()).getName());
                }
            }
        }
        //System.out.println(courseMap.toString());
        return courseMap.values();
    }

    /*获取特定课程*/
    public Course get(Integer id){
        return courseMap.get(id);
    }

    /*删除特定课程*/
    public void delete(Integer id){
        courseMapper.deleteCourse(id);
    }
}
