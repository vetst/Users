package com.users.mysql;

import com.users.exception.DBException;
import com.users.dao.UserDao;
import com.users.model.User;
import com.users.util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDao {

    private static MySqlUserDao INSTANCE;

    private MySqlUserDao() {

    }

    public static MySqlUserDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MySqlUserDao();
        }
        return INSTANCE;
    }

    public List<User> getAllUser() throws SQLException, DBException {

        List<User> resultList = new ArrayList<>();

        try (Connection connection = DBHelper.getInstance().getMysqlConnection()) {
            Statement stmt = connection.createStatement();
            stmt.execute("select * from users");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                resultList.add(new User(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return resultList;
    }

    public void addUser(User user) throws SQLException, DBException {

        try (Connection connection = DBHelper.getInstance().getMysqlConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into users (name, surname) values (?, ?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurName());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public void deleteUser(User user) throws SQLException, DBException {

        try (Connection connection = DBHelper.getInstance().getMysqlConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id =?");
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }


    public void updateUser(User user) throws SQLException, DBException {

        try (Connection connection = DBHelper.getInstance().getMysqlConnection()) {
            String updateQuery = "UPDATE users SET name =?, surname =? WHERE id =?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurName());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
