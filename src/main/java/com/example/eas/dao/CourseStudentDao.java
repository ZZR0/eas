package com.example.eas.dao;

import com.example.eas.entities.Course;
import com.example.eas.entities.CourseStudent;
import com.example.eas.entities.Teacher;
import com.example.eas.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*课程后台管理类，用于从数据库中获取课程学生信息*/
@Repository
public class CourseStudentDao {

    @Autowired
    private CourseMapper courseMapper;

    private static Map<Integer,CourseStudent> courseStudentMap = new HashMap<Integer, CourseStudent>();

    private static List<CourseStudent> courseStudents = null;

    public Collection<CourseStudent> getAllStudent(Course course){
        courseStudentMap.clear();
        courseStudents = courseMapper.getCourseStudent(course);
        for (CourseStudent courseStudent:courseStudents) {
            courseStudentMap.put(courseStudent.getId(),courseStudent);
        }
        //System.out.println(courseMap.toString());
        return courseStudentMap.values();
    }

    public Collection<Teacher> getAllTeacher(Course course){
        return courseMapper.getCourseTeacher(course);
    }

    public CourseStudent get(Integer id){
        return courseStudentMap.get(id);
    }

}
