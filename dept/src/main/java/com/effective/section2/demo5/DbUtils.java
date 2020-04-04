package com.effective.section2.demo5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/imooc";
    private static final String UNAME = "root";
    private static final String PWD = "root";


    //每次调用都实例化了一个Connection，差评
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获得数据库的连接
            conn = DriverManager.getConnection(URL, UNAME, PWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
