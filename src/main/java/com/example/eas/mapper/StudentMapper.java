package com.example.eas.mapper;

import com.example.eas.entities.StuCourse;
import com.example.eas.entities.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/*实现了所有课程操作相关的SQL语句及方法，可以直接调用相应的SQL语句对student，course_student两个表中的数据进行操作*/
@Repository
@Mapper
public interface StudentMapper {

    /*登录检测*/
    @Select("SELECT * FROM `student` where username = #{username} and password = #{password};")
    Student findByNameAndPassword(@Param("username") String username, @Param("password") String password);

    /*从数据库中获取所有学生*/
    @Select("SELECT * FROM `student`;")
    List<Student> selectAllStudent();

    /*向数据库中添加学生*/
    @Insert("INSERT INTO `student` (`id`, `name`, `username`, `password`, `email`, `phoneNumber`, `gender`, `departmentId`) VALUES (null, #{name}, #{username}, #{password}, #{email}, #{phoneNumber}, #{gender}, #{departmentId});")
    int insert(Student student);

    /*向数据库中更新学生信息*/
    @Update("UPDATE `student` SET `name` = #{name}, `username` = #{username}, `password` = #{password}, `email` = #{email}, `phoneNumber` = #{phoneNumber}, `gender` = #{gender}, `departmentId` = #{departmentId} WHERE `id` = #{id};")
    int editStudent(Student student);

    /*从数据库中删除学生*/
    @Delete("DELETE FROM `student` WHERE `id` = #{id};DELETE FROM `course_student` WHERE `studentid` = #{id};")
    int deleteStudent(@Param("id") Integer id);

    /*从数据库中获取学生课程*/
    @Select("SELECT " +
            "    c.id, c.courseCode, c.courseName, c.departmentId, cs.grade, t.name " +
            "FROM" +
            "    course_student AS cs " +
            "        JOIN " +
            "    course AS c ON cs.courseid = c.id " +
            "        JOIN " +
            "    course_teacher AS ct ON c.id = ct.courseid " +
            "        JOIN " +
            "    teacher AS t ON ct.teacherid = t.id " +
            "WHERE " +
            "    studentid = #{id};")
    List<StuCourse> selectStudentCoures(Student student);

    /*为学生添加课程*/
    @Insert("INSERT INTO course_student (`id`, `courseid`, `studentid`, `grade`) VALUES (null, #{cid}, #{sid}, 0);")
    int addCourse(@Param("cid") Integer cid, @Param("sid") Integer sid);

    /*学生课程退选*/
    @Delete("DELETE FROM `course_student` WHERE `courseid` = #{cid} AND studentid = #{sid};")
    int deleteStudentCourse(@Param("cid") Integer cid, @Param("sid") Integer sid);

    /*查询选课人数*/
    @Select("SELECT count(*) FROM `course_student` WHERE `courseid` = #{cid} AND studentid = #{sid};")
    Integer selectCount(@Param("cid") Integer cid, @Param("sid") Integer sid);
}
