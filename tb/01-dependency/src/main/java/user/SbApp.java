package user;

import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.dao.ConnectionMaker;
import user.dao.DConnectionMaker;
import user.dao.DaoFactory;
import user.dao.UserDao;
import user.domain.User;

public class SbApp {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao userDao = context.getBean(UserDao.class);

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
