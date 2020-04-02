package com.users.mysql;

import com.users.dao.UserDao;
import com.users.exception.DBException;
import com.users.model.User;
import com.users.util.DBHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDao {

    private Session session;

    public UserHibernateDAO() {

    }

    private Session getSession() {
        session = DBHelper.getInstance().getSessionFactory().openSession();
        return session;
    }

    @Override
    public List<User> getAllUser() throws SQLException, DBException {
        getSession();
        List<User> userList = null;
        Transaction transaction = session.beginTransaction();
        userList = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public void addUser(User user) throws SQLException, DBException {
        getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(User user) throws SQLException, DBException {
        getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE User WHERE id=:id");
        query.setParameter("id", user.getId());
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) throws SQLException, DBException {
        getSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }
}
