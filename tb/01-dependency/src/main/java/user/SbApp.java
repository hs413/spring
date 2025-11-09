package user;

import java.sql.Connection;
import java.sql.SQLException;
import user.dao.ConnectionMaker;
import user.dao.DConnectionMaker;
import user.dao.UserDao;
import user.domain.User;

public class SbApp {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        User user = new User();
        user.setId("user1");
        user.setPassword("123456");
        user.setName("user1");

        userDao.add(user);

        System.out.println(user.getId() + " " + user.getName() + " 등록");


        User user2 = userDao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println();


    }
}
