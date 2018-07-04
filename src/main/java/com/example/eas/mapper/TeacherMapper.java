package com.example.eas.mapper;

import com.example.eas.entities.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/*实现了所有课程操作相关的SQL语句及方法，可以直接调用相应的SQL语句对course，course_teacher两个表中的数据进行操作*/
@Repository
@Mapper
public interface TeacherMapper {

    /*教师登录检查*/
    @Select("SELECT * FROM `teacher` where username = #{username} and password = #{password};")
    Teacher findByNameAndPassword(@Param("username") String username, @Param("password") String password);

    /*从数据库中获取所有教师*/
    @Select("SELECT * FROM teacher;")
    List<Teacher> selectAllTeacher();

    /*新增教师*/
    @Insert("INSERT INTO `teacher` (`id`, `name`, `username`, `password`, `email`, `phoneNumber`, `gender`, `departmentId`) VALUES (null, #{name}, #{username}, #{password}, #{email}, #{phoneNumber}, #{gender}, #{departmentId});")
    int addTeacher(Teacher teacher);

    /*编辑教师信息*/
    @Update("UPDATE `teacher` SET `name` = #{name}, `username` = #{username}, `password` = #{password}, `email` = #{email}, `phoneNumber` = #{phoneNumber}, `gender` = #{gender}, `departmentId` = #{departmentId} WHERE `id` = #{id};")
    int editTeacher(Teacher teacher);

    /*删除教师*/
    @Delete("DELETE FROM `teacher` WHERE `id` = #{id};DELETE FROM `course_teacher` WHERE `teacherid` = #{id};")
    int deleteTeacher(@Param("id") Integer id);

    /*选择教师上的课程*/
    @Select("SELECT \n" +
            "    c.id, c.courseCode, c.courseName, c.departmentId, t.name\n" +
            "FROM\n" +
            "    teacher AS t\n" +
            "        JOIN\n" +
            "    course_teacher AS ct ON t.id = ct.teacherid\n" +
            "        JOIN\n" +
            "    course AS c ON ct.courseid = c.id\n" +
            "WHERE\n" +
            "    t.id = #{id};")
    List<TeacherCourse> selectTeacherCoures(Teacher teacher);

    /*教师退出课程*/
    @Delete("DELETE FROM `course_teacher` WHERE `courseid` = #{cid} AND teacherid = #{tid};")
    int deleteTeacherCourse(@Param("cid") Integer cid, @Param("tid") Integer tid);

    /*判断课程有无教师*/
    @Select("SELECT count(*) FROM `course_teacher` WHERE `courseid` = #{cid};")
    Integer checkCourse(@Param("cid") Integer cid);
}
