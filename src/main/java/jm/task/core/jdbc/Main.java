package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        List<User> userList = new ArrayList<>();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 20);
        System.out.println("User с именем – Ivan добавлен в базу данных");

        userService.saveUser("Petr", "Petrov", (byte) 21);
        System.out.println("User с именем – Petr добавлен в базу данных");

        userService.saveUser("Vasily", "Vasilyev", (byte) 22);
        System.out.println("User с именем – Vasily добавлен в базу данных");

        userService.saveUser("Sergey", "Sergeev", (byte) 23);
        System.out.println("User с именем – Sergey добавлен в базу данных");

        userList = userService.getAllUsers();
        for (User u : userList) {
            System.out.println(u);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
