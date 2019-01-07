package com.sec.demo.controller;

import com.sec.demo.pojo.User;
import com.sec.demo.util.AESUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by WangZJ on 2018/8/12.
 */
@Controller
public class UserController {

    @RequestMapping(value = "/sublogin",method = RequestMethod.POST)
    public String subLogin(String username,String password,HttpSession session,Model model){
        Subject subject = SecurityUtils.getSubject();
        try {
            password = AESUtil.encryptData(password);
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            subject.login(token);//验证角色和权限
            if (subject.isAuthenticated()){
                token.setRememberMe(true);
                System.out.println(username+" success login");
            }
        } catch (Exception e) {
        	return "login";
        }
        model.addAttribute("hello","Hello, Spring Boot!");
        return "index";
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String Test(){
    	System.out.println(10/1);
        return "okkkk！";
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println("logout...");
        return "login";
    }


    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "user/permissiontest")
    public String permissiontest(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "permissiontest";
    }


    @RequiresPermissions("user:user")
    @RequestMapping("user/list")
    public String userList(Model model) {
        model.addAttribute("value", "获取用户信息");
        return "user";
    }

    @RequiresPermissions("user:add")
    @RequestMapping("user/add")
    public String userAdd(Model model) {
        model.addAttribute("value", "新增用户");
        return "user";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("user/delete")
    public String userDelete(Model model) {
        model.addAttribute("value", "删除用户");
        return "user";
    }
}
