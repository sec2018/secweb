package com.sec.demo.shiro;

import com.sec.demo.generator.TPermissionMapper;
import com.sec.demo.generator.UserRoleMapper;
import com.sec.demo.pojo.Role;
import com.sec.demo.pojo.TPermission;
import com.sec.demo.pojo.User;
import com.sec.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by WangZJ on 2018/8/12.
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private TPermissionMapper tPermissionMapper;

    /**
     * 模拟数据库中取得的值
     */
    Map<String,String> userMap = new HashMap<>(16);

    {
        userMap.put("wang","123");
        super.setName("customRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        String username = (String)principalCollection.getPrimaryPrincipal();
//        //模拟从数据库或缓存中获取角色数据
//        Set<String> roles = getRoleByUserName(username);
//
//        Set<String> permissions = getPermissionsByUserName(username);
//
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.setStringPermissions(permissions);
//        simpleAuthorizationInfo.setRoles(roles);
//        return simpleAuthorizationInfo;


        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUsername();

        System.out.println("用户" + userName + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        List<Role> roleList = userRoleMapper.findByUsername(userName);
        Set<String> roleSet = new HashSet<String>();
        for (Role r : roleList) {
            roleSet.add(r.getRname());
        }
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        List<TPermission> permissionList = tPermissionMapper.findByUserName(userName);
        Set<String> permissionSet = new HashSet<String>();
        for (TPermission p : permissionList) {
            permissionSet.add(p.getName());
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

//    private Set<String> getPermissionsByUserName(String username) {
//        Set<String> sets = new HashSet<>();
//        sets.add("user:delete");
//        sets.add("user:add");
//        return sets;
//    }
//
//    private Set<String> getRoleByUserName(String username) {
//        Set<String> sets = new HashSet<>();
//        sets.add("admin");
//        sets.add("user");
//        return sets;
//    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

//        //1,从主体传过来的认证信息中，获得用户名
//        String userName = (String) authenticationToken.getPrincipal();
//
//        //2,通过用户名到数据库中获取凭证
////        String password = getPasswordByUserName(userName);
//        String password = getPasswordByUserNameFromDb(userName);
//        if(password == null) {
//            return null;
//        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,password,"customRealm");
//        return authenticationInfo;

        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        System.out.println("用户" + userName + "认证-----ShiroRealm.doGetAuthenticationInfo");
        User user = userService.findUserByName(userName);

        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if (user.getStatus().equals("0")) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    /*
        模拟数据库查询验证
     */
    private String getPasswordByUserName(String userName) {
        return userMap.get(userName);
    }


    /**
     * 真实数据库中取值
     */
    private String getPasswordByUserNameFromDb(String userName) {
        return userService.getPasswordByUserName(userName);
    }
}
