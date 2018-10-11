package com.diguikeji.selenium;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlConnect {

    public static void insert(String id,String name,String num1) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://122.112.253.11:3306/zhi");
            dataSource.setUser("root");
            dataSource.setPassword("@!diguikeji123");
            conn = dataSource.getConnection();
            String sql = "INSERT INTO tb_product (id,name,num1) VALUES (?,?,?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3,num1);
            pstmt.executeUpdate();
        }catch (Exception e){
           e.printStackTrace();
            System.out.println(e);
        }finally {
         /*  JDBCUtils.release(rs,pstmt,conn);*/
        }
    }


}