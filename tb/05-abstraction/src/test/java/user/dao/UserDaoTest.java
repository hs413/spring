package user.dao;


import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import user.domain.Level;
import user.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserDaoTest {
    @Autowired
    UserDao dao;

    @Autowired
    DataSource dataSource;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
        this.user1 = new User("user1", "user1_name", "user1_pw", Level.BASIC, 1, 0);
        this.user2 = new User("user2", "user2_name", "user2_pw", Level.SILVER, 55, 10);
        this.user3 = new User("user3", "user3_name", "user3_pw", Level.GOLD, 100, 40);
    }

    @Test
    public void update() {
        dao.deleteAll();

        dao.add(user1);

        user1.setName("user4_name");
        user1.setPassword("user4_pw");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);
        dao.update(user1);

        User user1update = dao.get(user1.getId());
        checkSameUser(user1, user1update);
    }


//    @Test
//    public void andAndGet() {
//        dao.deleteAll();
//        assertThat(dao.getCount()).isEqualTo(0);
//
//        dao.add(user1);
//        dao.add(user2);
//        assertThat(dao.getCount()).isEqualTo(2);
//
//        User userget1 = dao.get(user1.getId());
//        checkSameUser(userget1, user1);
//
//        User userget2 = dao.get(user2.getId());
//        checkSameUser(userget2, user2);
//    }
//
//    @Test(expected=EmptyResultDataAccessException.class)
//    public void getUserFailure() throws SQLException {
//        dao.deleteAll();
//        assertThat(dao.getCount(), is(0));
//
//        dao.get("unknown_id");
//    }


//    @Test
//    public void count() {
//        dao.deleteAll();
//        assertThat(dao.getCount(), is(0));
//
//        dao.add(user1);
//        assertThat(dao.getCount(), is(1));
//
//        dao.add(user2);
//        assertThat(dao.getCount(), is(2));
//
//        dao.add(user3);
//        assertThat(dao.getCount(), is(3));
//    }

//    @Test
//    public void getAll()  {
//        dao.deleteAll();
//
//        List<User> users0 = dao.getAll();
//        assertThat(users0.size(), is(0));
//
//        dao.add(user1); // Id: gyumee
//        List<User> users1 = dao.getAll();
//        assertThat(users1.size(), is(1));
//        checkSameUser(user1, users1.get(0));
//
//        dao.add(user2); // Id: leegw700
//        List<User> users2 = dao.getAll();
//        assertThat(users2.size(), is(2));
//        checkSameUser(user1, users2.get(0));
//        checkSameUser(user2, users2.get(1));
//
//        dao.add(user3); // Id: bumjin
//        List<User> users3 = dao.getAll();
//        assertThat(users3.size(), is(3));
//        checkSameUser(user3, users3.get(0));
//        checkSameUser(user1, users3.get(1));
//        checkSameUser(user2, users3.get(2));
//    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
        assertThat(user1.getLevel()).isEqualTo(user2.getLevel());
        assertThat(user1.getLogin()).isEqualTo(user2.getLogin());
        assertThat(user1.getRecommend()).isEqualTo(user2.getRecommend());
    }
//
//    @Test(expected=DuplicateKeyException.class)
//    public void duplciateKey() {
//        dao.deleteAll();
//
//        dao.add(user1);
//        dao.add(user1);
//    }
//
//    @Test
//    public void sqlExceptionTranslate() {
//        dao.deleteAll();
//
//        try {
//            dao.add(user1);
//            dao.add(user1);
//        }
//        catch(DuplicateKeyException ex) {
//            SQLException sqlEx = (SQLException)ex.getCause();
//            SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
//            DataAccessException transEx = set.translate(null, null, sqlEx);
//            assertThat(transEx, is(DuplicateKeyException.class));
//        }
//    }
}