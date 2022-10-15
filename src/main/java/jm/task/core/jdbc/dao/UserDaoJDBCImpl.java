package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String command;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        command = "CREATE TABLE IF NOT EXISTS Users\n" +
                "(\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    name VARCHAR(30) NOT NULL,\n" +
                "    lastName VARCHAR(30) NOT NULL,\n" +
                "    age INT\n" +
                ");";
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(command);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        command = "DROP TABLE IF EXISTS Users;";
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(command);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        command = String.format
                ("INSERT INTO Users (name, lastName, age) VALUES ('%s', '%s', %d);", name, lastName, age);
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(command);
            System.out.println(String.format("User с именем – %s добавлен в базу данных", name));
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        command = String.format("DELETE FROM Users WHERE id = %d", id);
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(command);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        command = "SELECT * FROM Users";
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(command);
            while (true) {
                try {
                    if (resultSet == null) break;
                    if (!resultSet.next()) break;
                    User newUser = new User(resultSet.getLong("id"), resultSet.getString("name")
                            , resultSet.getString("lastName"), resultSet.getByte("age"));
                    users.add(newUser);
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return users;
    }


    public void cleanUsersTable() {
        command = "DELETE  FROM Users;";
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(command);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


}
