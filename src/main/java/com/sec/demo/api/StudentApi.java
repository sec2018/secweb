package com.sec.demo.api;

import com.sec.demo.dao.StudentDao;
import com.sec.demo.pojo.Student;
import com.sec.demo.util.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("api")
@Controller
public class StudentApi {

    @Autowired
    private StudentDao studentDao;

    @ApiOperation(value = "获取所有学生信息接口", notes = "获取所有学生信息接口")
    @RequestMapping(value = "getallstudents",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<JsonResult> searchSysUserAddr(){
        JsonResult r = new JsonResult();
        try {
            List<Student> addrList = studentDao.findAllStudent();
            r.setCode("200");
            r.setMsg("查询成功！");
            r.setData(addrList);
            r.setSuccess(true);
        } catch (Exception e) {
            r.setCode("500");
            r.setMsg("查询失败！");
            r.setData(null);
            r.setSuccess(true);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}
