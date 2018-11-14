package com.sec.demo.service.impl;

import com.sec.demo.pojo.Equipmenttype;
import com.sec.demo.repository.EquipmenttypeRepository;
import com.sec.demo.service.EquipmenttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EquipmenttypeServiceImpl implements EquipmenttypeService{

    @Resource(name="equipmenttypeRepository")
    private EquipmenttypeRepository equipmenttypeRepository;

    //另外一种写法
//    @Autowired
//    @Qualifier("equipmenttypeRepository")
//    private EquipmenttypeRepository equipmenttypeRepository;

    @Override
    public Equipmenttype findById(Integer id) {
        return equipmenttypeRepository.getOne(id);
    }
}
