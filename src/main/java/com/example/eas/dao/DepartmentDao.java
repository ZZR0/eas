package com.example.eas.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.eas.entities.Department;
import com.example.eas.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*课程后台管理类，用于从数据库中获取部门信息*/
@Repository
public class DepartmentDao {

	@Autowired
	DepartmentMapper departmentMapper;
	private static Map<Integer, Department> departmentMap = new HashMap<Integer, Department>();
	private static List<Department> departments = null;

	
	public Collection<Department> getDepartments(){
		departmentMap.clear();
		departments = departmentMapper.allDepartment();
		for (Department department:departments) {
			departmentMap.put(department.getId(),department);
		}
		return departmentMap.values();
	}
	
	public String getDepartment(Integer id){
		return departmentMap.get(id).getDepartmentName();
	}
	
}
