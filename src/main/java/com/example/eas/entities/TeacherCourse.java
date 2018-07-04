package com.example.eas.entities;

/*教师课程对象*/
public class TeacherCourse {


    private Integer id;
    private String courseCode;
    private String courseName;
    private Integer count=0;
    private Integer departmentId;
    private String departmentName;
    private String name;

    public TeacherCourse(Integer id, String courseCode, String courseName, Integer count, Integer departmentId, String name) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.count = count;
        this.departmentId = departmentId;
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public TeacherCourse() {
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    @Override
    public String toString() {
        return "StuCourse{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", count=" + count +
                ", departmentId='" + departmentId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
