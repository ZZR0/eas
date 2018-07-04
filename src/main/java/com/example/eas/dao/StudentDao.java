package com.example.eas.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.eas.entities.Department;
import com.example.eas.entities.Student;
import com.example.eas.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*课程后台管理类，用于从数据库中获取学生信息*/
@Repository
public class StudentDao {
	
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private StudentMapper studentMapper;

	private static Map<Integer, Student> employees = new HashMap<Integer, Student>();

	private static List<Student> students = null;
	
	public void save(Student student){
		if(student.getId() == null){
			studentMapper.insert(student);
		}else {
			studentMapper.editStudent(student);
		}
	}
	
	public Collection<Student> getAll(){
		employees.clear();
		students = studentMapper.selectAllStudent();
		departmentDao.getDepartments();
		for (Student student:students) {
			student.setDepartmentName(departmentDao.getDepartment(student.getDepartmentId()));
			employees.put(student.getId(), student);
		}
		return employees.values();
	}
	
	public Student get(Integer id){
		getAll();
		return employees.get(id);
	}
	
	public void delete(Integer id){
		int i = studentMapper.deleteStudent(id);
		if(i==1) employees.remove(id);
	}
}
