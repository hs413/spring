package user.dao;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.domain.User;

class UserDaoTest {
    @Test
    void addAndGet() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = (UserDao) context.getBean("userDao");

        User user = new User();
        user.setId("if");
        user.setName("name");
        user.setPassword("password");

        dao.add(user);

        User user2 = dao.get("if");
        assertThat(user2.getId()).isEqualTo(user.getId());
        assertThat(user2.getPassword()).isEqualTo(user.getPassword());


        dao.deleteAll();

        assertThat(dao.getCount()).isEqualTo(0);


    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = (UserDao) context.getBean("userDao");

        User user1 = new User("1", "user1", "password1");
        User user2 = new User("2", "user2", "password2");
        User user3 = new User("3", "user3", "password3");

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);


    }

    @Test
    public void getFail() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = (UserDao) context.getBean("userDao");

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);


        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("if"));
    }
}