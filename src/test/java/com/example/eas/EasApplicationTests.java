package com.example.eas;

import com.example.eas.dao.StudentDao;
import com.example.eas.entities.Course;
import com.example.eas.mapper.CourseMapper;
import com.example.eas.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasApplicationTests {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StudentDao studentDao;
    @Autowired
    CourseMapper courseMapper;
    @Test
    public void contextLoads() {
        studentMapper.selectCount(1,2);
    }

}
