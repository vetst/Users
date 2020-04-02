package com.users.service;

import com.users.dao.UserDao;
import com.users.exception.DBException;
import com.users.model.User;
import com.users.mysql.UserHibernateDAO;

import java.util.List;

public class UserService {

    private static UserService INSTANCE;

    private UserService() {

    }

    public static UserService getUserService() {
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

private UserDao userDao  = new UserHibernateDAO();

    public boolean addUser(User user) throws DBException {

        try {
            userDao.addUser(user);
            return true;
        } catch (Exception e) {
            throw new DBException("Не могу добавить пользователя", e);
        }
    }

    public boolean updateUser(User user) throws DBException {
        try {
            userDao.updateUser(user);
            return true;
        } catch (Exception e) {
            throw new DBException("Не могу обновить пользователя", e);
        }
    }

    public boolean deleteUser(User user) {
        try {
            userDao.deleteUser(user);
            return true;
        } catch (Exception e) {
            new DBException("Не могу удалить пользователя", e);
        }
        return false;
    }

    public List<User> getAllUser() throws DBException {
        try {
            return userDao.getAllUser();
        } catch (Exception e) {
            throw new DBException("Не могу получить список пользователей", e);
        }
    }

}
