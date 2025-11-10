package user.dao;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.domain.User;

class UserDaoTest {
    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;


    @BeforeEach
    public void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.dao = (UserDao) context.getBean("userDao");

        this.user1 = new User("1", "user1", "password1");
        this.user2 = new User("2", "user2", "password2");
        this.user3 = new User("3", "user3", "password3");
    }

    @Test
    void addAndGet() throws SQLException, ClassNotFoundException {
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
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);


        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("if"));
    }
}