package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static String urlDb = "jdbc:mysql://localhost:3306/testdb";
    public static String userName = "root";
    public static String password = "root";

    public static Connection getConnection(){
        Connection connection = null;
        try{connection = DriverManager.getConnection(urlDb, userName, password);}
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return connection;
    }
}
