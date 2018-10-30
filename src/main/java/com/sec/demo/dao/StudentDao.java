package com.sec.demo.dao;

import com.sec.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentDao {

    @Select("select * from student")
    List<Student> findAllStudent();
}
