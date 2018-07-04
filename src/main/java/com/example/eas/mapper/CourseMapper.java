package com.example.eas.mapper;

import com.example.eas.entities.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/*实现了所有课程操作相关的SQL语句及方法，可以直接调用相应的SQL语句对course，course_student，course_teacher三个表中的数据进行操作*/
@Repository
@Mapper
public interface CourseMapper {

    /*从数据库中获取所有课程*/
    @Select("SELECT \n" +
            "    c.id, c.courseCode, c.courseName, c.courseCapacity, c.departmentId, t.name\n" +
            "FROM\n" +
            "    course AS c\n" +
            "        JOIN\n" +
            "    course_teacher AS ct ON c.id = ct.courseid\n" +
            "        JOIN\n" +
            "    teacher AS t ON t.id = ct.teacherid;")
    List<Course> selectAllCourse();

    /*从数据库中删除某门课程的老师*/
    @Delete("DELETE FROM course_teacher WHERE courseid = #{cid};")
    void clearCourseTeacher(@Param("cid") Integer cid);

    /*获取课程的选课人数*/
    @Select("SELECT COUNT(*) FROM course_student AS cs WHERE cs.courseid = #{id};")
    Integer countStudent(Course course);

    /*获取课程的选课人数*/
    @Select("SELECT COUNT(*) FROM course_student AS cs WHERE cs.courseid = #{id};")
    Integer idCountStudent(@Param("id") Integer id);

    /*在数据库中加课*/
    @Insert("INSERT INTO `course` (`id`, `courseCode`, `courseName`, `courseCapacity`, `departmentId`) VALUES (null, #{courseCode}, #{courseName}, #{courseCapacity}, #{departmentId});")
    int addCourse(Course course);

    /*为课程添加教师*/
    @Insert("INSERT INTO `course_teacher` (`id`, `courseid`, `teacherid`) VALUES (null, #{cid}, #{tid});")
    void addCourseTeacher(@Param("cid") Integer cid, @Param("tid") Integer tid);

    /*删除特定课程*/
    @Delete("DELETE FROM `course` WHERE `id` = #{id};DELETE FROM `course_teacher` WHERE `courseid` = #{id};DELETE FROM `course_student` WHERE `courseid` = #{id};")
    int deleteCourse(@Param("id") Integer id);

    /*更新课程信息*/
    @Update("UPDATE `course` SET `courseCode` = #{courseCode}, `courseName` = #{courseName}, `courseCapacity` = #{courseCapacity}, `departmentId` = #{departmentId} WHERE `id` = #{id};")
    int editCourse(Course course);

    /*获取课程id*/
    @Select("SELECT c.id FROM course AS c WHERE c.courseCode = #{courseCode};")
    Integer getCourseId(Course course);

    /*获取选课学生*/
    @Select("SELECT \n" +
            "    s.id, s.name, s.gender, d.departmentName, s.email, s.phoneNumber, cs.grade\n" +
            "FROM\n" +
            "    course AS c\n" +
            "        JOIN\n" +
            "    course_student AS cs ON c.id = cs.courseid\n" +
            "        JOIN\n" +
            "    student AS s ON cs.studentid = s.id\n" +
            "        JOIN\n" +
            "    department AS d ON s.departmentId = d.id\n" +
            "    where c.id=#{id};")
    List<CourseStudent> getCourseStudent(Course course);

    /*获取课程教师*/
    @Select("SELECT \n" +
            "    t.id, t.name, t.email, t.phoneNumber, t.gender, d.departmentName\n" +
            "FROM\n" +
            "    course AS c\n" +
            "        JOIN\n" +
            "    course_teacher AS ct ON c.id = ct.courseid\n" +
            "        JOIN\n" +
            "    teacher AS t ON t.id = ct.teacherid\n" +
            "        JOIN\n" +
            "    department AS d ON d.id = t.departmentId\n" +
            "WHERE\n" +
            "    c.id = #{id};")
    List<Teacher> getCourseTeacher(Course course);

    /*获取学生成绩*/
    @Delete("DELETE FROM `course_student` WHERE `courseid` = #{cid} AND studentid = #{sid};INSERT INTO course_student (`id`, `courseid`, `studentid`, `grade`) VALUES (null, #{cid}, #{sid}, #{grade});")
    int editGrade(CSG csg);
}
