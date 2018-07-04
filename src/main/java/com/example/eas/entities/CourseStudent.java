package com.example.eas.entities;

/*课程的学生对象*/
public class CourseStudent {

    private Integer id;
    private String name;
    //1 male, 0 female
    private Integer gender;
    private String email;
    private String phoneNumber;
    private Integer grade;
    private String departmentName;

    @Override
    public String toString() {
        return "CourseStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", grade=" + grade +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public CourseStudent(Integer id, String name, Integer gender, Integer grade, String departmentName) {

        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.departmentName = departmentName;
    }

    public CourseStudent() {

    }
}
