package com.example.eas.dao;

import com.example.eas.entities.Teacher;
import com.example.eas.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*课程后台管理类，用于从数据库中获取教师信息*/
@Repository
public class TeacherDao {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private TeacherMapper teacherMapper;

    private static Map<Integer, Teacher> teacherMap = new HashMap<Integer, Teacher>();

    private static List<Teacher> teachers = null;

    public void save(Teacher teacher){
        int i;
        if(teacher.getId() == null){
            i = teacherMapper.addTeacher(teacher);
        }else {
            i = teacherMapper.editTeacher(teacher);
        }
        if(i==1) teacherMap.put(teacher.getId(), teacher);
    }

    public Collection<Teacher> getAll(){
        teacherMap.clear();
        teachers = teacherMapper.selectAllTeacher();
        departmentDao.getDepartments();
        for (Teacher teacher:teachers) {
            teacher.setDepartmentName(departmentDao.getDepartment(teacher.getDepartmentId()));
            teacherMap.put(teacher.getId(), teacher);
        }
        return teacherMap.values();
    }

    public Teacher get(Integer id){
        getAll();
        return teacherMap.get(id);
    }

    public void delete(Integer id){
        int i = teacherMapper.deleteTeacher(id);
        if(i==1) teacherMap.remove(id);
    }
}
