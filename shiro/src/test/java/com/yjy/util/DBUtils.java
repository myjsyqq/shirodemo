package com.yjy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtils {
    public final static String URL = "com.mysql.cj.jdbc.Driver";
    public final static String DRIVER = "jdbc:mysql://localhost:3306/pro?serverTimezone=UTC";
    public final static String USERNAME = "root";
    public final static String PASSWORD = "root";


    public static Connection getCon() throws Exception {
        Class.forName(URL);
        Connection con = DriverManager.getConnection(DRIVER, USERNAME, PASSWORD);
        return con;
    }

    public static ResultSet doQuery(String sql, Object...param){
        ResultSet rs = null;
        try {
            Connection con = getCon();
            PreparedStatement pstmt = con.prepareStatement(sql);
            if (param != null || param.length>0){
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i+1,param[i]);
                }
            }
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    //封装一个通用的增删改方法
    public static int doUpdate(String sql,Object...param){
        int rs = 0;
        try {
            Connection con = getCon();
            PreparedStatement pstmt = con.prepareStatement(sql);
            if (param != null || param.length>0){
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i+1,param[i]);
                }
            }
            rs = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
