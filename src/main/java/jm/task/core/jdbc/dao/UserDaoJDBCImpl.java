package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("create table IF NOT exists user (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, age INT NOT NULL, PRIMARY KEY (id))");
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS user");
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO user(name, lastName, age) VALUES(?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String query = "DELETE FROM user WHERE id=?";
        try(Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try(Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String n = rs.getString("name");
                String ln = rs.getString("lastName");
                Byte a = rs.getByte("age");
                User u = new User(n, ln, a);
                u.setId(id);
                usersList.add(u);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE user")){
            preparedStatement.execute();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}