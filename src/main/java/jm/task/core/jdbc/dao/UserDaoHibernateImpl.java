package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    private String command;

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        command = "CREATE TABLE IF NOT EXISTS Users\n" +
                "(\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    name VARCHAR(30) NOT NULL,\n" +
                "    lastName VARCHAR(30) NOT NULL,\n" +
                "    age INT\n" +
                ");";
        Query query = session.createSQLQuery(command).addEntity(User.class);
        query.executeUpdate();
        System.out.println("ok");
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        command = "DROP TABLE IF EXISTS Users;";
        Query query = session.createSQLQuery(command).addEntity(User.class);
        System.out.println("ok");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        command = String.format
                ("INSERT INTO Users (name, lastName, age) VALUES ('%s', '%s', %d);", name, lastName, age);
        Query query = session.createSQLQuery(command).addEntity(User.class);
        query.executeUpdate();
        System.out.println(String.format("User с именем – %s добавлен в базу данных", name));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        command = String.format("DELETE FROM Users WHERE id = %d", id);
        Query query = session.createSQLQuery(command).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        command = "SELECT * FROM Users";
//        List<User> res = (List<User>) session.createSQLQuery(command).list();
        Query query = session.createSQLQuery(command).addEntity(User.class);
        List<User> res = query.list();
//        query.executeUpdate();
        transaction.commit();
        session.close();

        return res;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        command = "DELETE  FROM Users;";
        Query query = session.createSQLQuery(command).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
