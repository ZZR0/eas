package com.example.eas.mapper;

import com.example.eas.entities.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/*从admin表获取特定数据*/
@Repository
@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM `admin` where username = #{username} and password = #{password};")
    Admin findByNameAndPassword(@Param("username") String username, @Param("password") String password);
}
