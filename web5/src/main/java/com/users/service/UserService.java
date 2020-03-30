package com.users.service;

import com.users.dao.DBException;
import com.users.dao.UserDao;
import com.users.model.User;
import com.users.mysql.MySqlUserDao;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    UserDao userDao = new MySqlUserDao();

    public boolean addUser(User user) throws DBException {

        try {
            userDao.addUser(user);
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean updateUser(User user) throws DBException {
        try {
            userDao.updateUser(user);
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean deleteUser(User user) {
        try {
            userDao.deleteUser(user);
            return true;
        } catch (SQLException e) {
            new DBException(e);
        }
        return false;
    }

    public List<User> getAllUser() throws DBException {
        try {
            return userDao.getAllUser();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

}
