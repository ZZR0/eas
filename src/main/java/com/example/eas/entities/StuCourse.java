package com.example.eas.entities;

import com.example.eas.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;

/*学生课程对象*/
public class StuCourse {

    @Autowired
    DepartmentDao departmentDao;

    private Integer id;
    private String courseCode;
    private String courseName;
    private Integer grade;
    private Integer departmentId;
    private String departmentName;
    private String name;

    public StuCourse(Integer id, String courseCode, String courseName, Integer grade, Integer departmentId, String name) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.grade = grade;
        this.departmentId = departmentId;
        this.name = name;
    }


    public StuCourse() {
    }

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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "StuCourse{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", grade=" + grade +
                ", departmentId='" + departmentId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
