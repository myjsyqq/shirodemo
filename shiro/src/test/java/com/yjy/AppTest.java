package com.yjy;

import static org.junit.Assert.assertTrue;

import com.yjy.dao.UsersDao;
import com.yjy.entity.Users;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shiro() {
        //1、获取工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、创建securityManager对象
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、获取主体对象
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zs","1111");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
//        //1、基于角色的授权
//        boolean flag = subject.hasRole("role1");
//        System.out.println("授权结果"+flag);
//        //判断是否有多个角色
//        boolean[] flags = subject.hasRoles(Arrays.asList("role1", "role2"));
//        for (boolean b : flags) {
//            System.out.println(b);
//        }
        //2、基于资源的授权
        boolean flag = subject.isPermitted("user:update");
        System.out.println(flag);
        boolean flags = subject.isPermittedAll("user:add", "user:delete", "user:update", "user:findAll");
        System.out.println(flags);
    }

    public static void main(String[] args) {
        UsersDao usersDao = new UsersDao();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.next();
        System.out.println("请输入密码：");
        String password = sc.next();
        String salt = "salt";
        String password_salt_2 = new Md5Hash(password, salt, 2).toString();
        Users users = new Users(username,password_salt_2,salt);
        usersDao.add(users);
    }
}
