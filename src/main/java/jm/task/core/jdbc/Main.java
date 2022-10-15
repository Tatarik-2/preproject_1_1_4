package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.createUsersTable();

//        userDaoJDBC.saveUser("Ivan", "Petrov", (byte) 25);
//        userDaoJDBC.saveUser("mark", "lost", (byte) 21);
//        userDaoJDBC.saveUser("boom", "rob", (byte) 28);
//        userDaoJDBC.saveUser("Piter", "Parker", (byte) 15);
//
//        List<User> list = userDaoJDBC.getAllUsers();
//        for (User user : list
//        ) {
//            System.out.println(user);
//        }
//
//        userDaoJDBC.cleanUsersTable();
//        userDaoJDBC.dropUsersTable();
//
//        System.out.println("norm");

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
//        userDaoHibernate.createUsersTable();

//        userDaoHibernate.dropUsersTable();
////
//        userDaoHibernate.saveUser("ivanj", "smith", (byte) 24);
//        userDaoHibernate.saveUser("hoo", "fgh", (byte) 29);
//        userDaoHibernate.saveUser("guu", "qwe", (byte) 21);
//        userDaoHibernate.saveUser("fuu", "asd", (byte) 14);
//
//        userDaoHibernate.removeUserById(2);
//
        List<User> list = userDaoHibernate.getAllUsers();
        for (User u : list
        ) {
            System.out.println(u);
        }
    }
}
