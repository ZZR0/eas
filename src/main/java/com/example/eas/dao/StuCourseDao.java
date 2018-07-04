package com.example.eas.dao;

import com.example.eas.entities.StuCourse;
import com.example.eas.entities.Student;
import com.example.eas.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*课程后台管理类，用于从数据库中获取学生课程信息*/
@Repository
public class StuCourseDao {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private StudentMapper studentMapper;

    private static Map<Integer, StuCourse> stuCourseMap = new HashMap<Integer, StuCourse>();

    private static List<StuCourse> stuCourses = null;


    public int save(Integer cid, Integer sid){
        int i = studentMapper.selectCount(cid,sid);
        if (i==0) studentMapper.addCourse(cid,sid);
        return i;
    }

    public Collection<StuCourse> getAll(Student student){
        stuCourseMap.clear();
        stuCourses = studentMapper.selectStudentCoures(student);
        departmentDao.getDepartments();
        //把数据从数据库中取出并放入一个Map中
        for (StuCourse stuCourse:stuCourses) {
            stuCourse.setDepartmentName(departmentDao.getDepartment(stuCourse.getDepartmentId()));
            if (stuCourseMap.get(stuCourse.getId())==null) {
                stuCourseMap.put(stuCourse.getId(), stuCourse);
            }else {
                if (!stuCourseMap.get(stuCourse.getId()).getName().contains(stuCourse.getName())){
                    stuCourseMap.get(stuCourse.getId()).setName(stuCourse.getName()+", "+stuCourseMap.get(stuCourse.getId()).getName());
                }
            }
        }
        //System.out.println(stuCourseMap.toString());
        return stuCourseMap.values();
    }

    public Map<Integer, StuCourse> getAllMap(Student student){
        stuCourseMap.clear();
        getAll(student);
        return stuCourseMap;
    }

    public StuCourse get(Integer id){
        return stuCourseMap.get(id);
    }

    public void delete(Integer cid, Integer sid){
        studentMapper.deleteStudentCourse(cid,sid);
    }
}
