package com.yjy.realm;

import com.yjy.dao.UsersDao;
import com.yjy.entity.Users;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //获取用户信息
        String username = principal.getPrimaryPrincipal().toString();
        //根据用户信息去查询对应的权限--我使用假数据
        List<String> permission = new ArrayList<>();
        permission.add("user:add");
        permission.add("user:delete");
        permission.add("user:update");
        permission.add("user:findAll");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (String s : permission) {
            info.addStringPermission(s);
        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户输入的用户名
        String username = (String)token.getPrincipal();
        //根据用户名将密码查询出来
        UsersDao usersDao = new UsersDao();
        Users users = usersDao.findByPassword(username);
        String password = users.getPassword();
        String salt = users.getPassword_salt();
        //具体的匹配让它自己匹配
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,ByteSource.Util.bytes(salt),getName());
        return info;
    }
}
