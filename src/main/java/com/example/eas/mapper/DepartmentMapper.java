package com.example.eas.mapper;

import com.example.eas.entities.Admin;
import com.example.eas.entities.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartmentMapper {

    /*从数据库中获取所有部门*/
    @Select("SELECT * FROM `department`;")
    List<Department> allDepartment();
}
