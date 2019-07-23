package com.light.sword;

import java.sql.*;

public class JdbcDemo {
    final static String URL = "jdbc:mysql://127.0.0.1:3306/test";
    final static String USERNAME = "root";
    final static String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM product";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("name"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
