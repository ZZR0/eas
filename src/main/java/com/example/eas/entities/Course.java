package com.example.eas.entities;

import com.example.eas.dao.DepartmentDao;
import com.example.eas.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*课程对象*/
public class Course {

    @Autowired
    DepartmentDao departmentDao;

    private Integer id;
    private String courseCode;
    private String courseName;
    private Integer courseCapacity;
    private Integer departmentId;
    private String departmentName;
    private String name;
    private Integer count = 0;
    private List<Integer> teacher = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseCapacity() {
        return courseCapacity;
    }

    public void setCourseCapacity(Integer courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Integer> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<Integer> teacher) {
        this.teacher = teacher;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Course(Integer id, String courseCode, String courseName, Integer courseCapacity, Integer departmentId, String name) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCapacity = courseCapacity;
        this.departmentId = departmentId;
        this.name = name;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "departmentDao=" + departmentDao +
                ", id=" + id +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseCapacity=" + courseCapacity +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", teacher=" + teacher +
                '}';
    }
}
