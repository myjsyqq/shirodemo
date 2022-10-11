package com.yjy.dao;

import com.yjy.entity.Users;
import com.yjy.util.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDao {
    public void add(Users users){
        String sql = "insert into users(username,password,password_salt) values(?,?,?)";
        DBUtils.doUpdate(sql,users.getUsername(),users.getPassword(),users.getPassword_salt());
    }
    public Users findByPassword(String username){
        String sql = "select password,password_salt from users where username = ?";
        ResultSet rs = DBUtils.doQuery(sql, username);
        Users users = null;
        try {
            while (rs.next()){
                String password = rs.getString("password");
                String password_salt = rs.getString("password_salt");
                users = new Users(password,password_salt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
