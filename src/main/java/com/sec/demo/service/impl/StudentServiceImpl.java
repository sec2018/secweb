package com.sec.demo.service.impl;

import com.sec.demo.dao.StudentDao;
import com.sec.demo.pojo.Student;
import com.sec.demo.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("studentSerice")
public class StudentServiceImpl implements StudentService{

    @Resource
    private StudentDao studentDao;

    @Override
    public List<Student> findAllStudent() {
        return studentDao.findAllStudent();
    }
}
