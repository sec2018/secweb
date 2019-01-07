package com.sec.demo.shiro;

import com.sec.demo.util.AESUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;

/**
 * Created by WangZJ on 2018/8/11.
 */
public class AuthenticationTest {

//    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
//
//    public void addUser(){
//        simpleAccountRealm.addAccount("wang","123","admin","user");
//    }

    CustomRealm customRealm = new CustomRealm();

    public void testAuthentication(){

        //1,构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
//        defaultSecurityManager.setRealm(simpleAccountRealm);
        defaultSecurityManager.setRealm(customRealm);


        //2,主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        String password = "123";
//        try {
//            password = AESUtil.encryptData("123");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        UsernamePasswordToken token = new UsernamePasswordToken("wang",password);
        subject.login(token);

        System.out.println("isAuthentication: "+ subject.isAuthenticated());
//        subject.checkRoles("admin","user");

        subject.checkRoles("admin");
        subject.checkPermissions("user:add","user:delete");
        subject.logout();
        System.out.println("isAuthentication: "+ subject.isAuthenticated());
    }

    public static void main(String[] args) {
        AuthenticationTest auth = new AuthenticationTest();
//        auth.addUser();
        auth.testAuthentication();

    }
}
