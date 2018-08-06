package com.aubga.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlInsertTest {
	public static void main(String[] arg){  
        Connection conn;  
        PreparedStatement stmt;  
        String driver = "com.mysql.jdbc.Driver";  
        String url = "jdbc:mysql://10.103.27.88:3306/test?useSSL=false";  
        String user = "gtp";  
        String password = "123456";  
        String sql = "insert into xdual(x) values ('201-10-11 22:22:22')";  
          
        try {  
            Class.forName(driver); 
            long start = System.currentTimeMillis();
            System.out.println(start);
            conn = DriverManager.getConnection(url, user, password);  
            long middle = System.currentTimeMillis();
            System.out.println(middle-start);
            stmt = (PreparedStatement) conn.prepareStatement(sql);  
            long end = System.currentTimeMillis();
            System.out.println(end-middle);
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
