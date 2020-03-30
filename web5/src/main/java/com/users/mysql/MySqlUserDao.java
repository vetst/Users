package com.users.mysql;

import com.users.dao.DBException;
import com.users.dao.UserDao;
import com.users.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDao {
    private Connection connection;

    public MySqlUserDao() {
        connection = getMysqlConnection();
    }

    public List<User> getAllUser() throws SQLException {
        List<User> resultList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("select * from users");
            rs = stmt.getResultSet();
            while (rs.next()) {
                resultList.add(new User(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
                /*NOP*/
            }
        }
        return resultList;
    }

    public void addUser(User user) throws SQLException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("insert into users (name, surname) values (?, ?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurName());
            statement.executeUpdate();
        } finally {
            try {

                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                /*NOP*/
            }
        }
    }

    public void deleteUser(User user) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM users WHERE id =?");
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
                /*NOP*/
            }
        }
    }


    public void updateUser(User user) throws SQLException, DBException {
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            String updateQuery = "UPDATE users SET name =?, surname =? WHERE id =?";
            statement = connection.prepareStatement(updateQuery);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurName());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            /*NOP*/
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
                /*NOP*/
            }
        }
    }

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_user?").          //db name
                    append("user=root&").          //login
                    append("password=root");

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
