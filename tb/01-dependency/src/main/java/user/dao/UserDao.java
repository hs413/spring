package user.dao;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import user.domain.User;

public class UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource DataSource) {
        this.jdbcTemplate = new JdbcTemplate(DataSource);
        this.dataSource = DataSource;
    }


    public void add(User user) throws ClassNotFoundException, SQLException {
        this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
                user.getId(), user.getName(), user.getPassword());
    }

    //    public User get(String id) throws ClassNotFoundException, SQLException {
//        Connection c = connectionMaker.makeNewConnection();
//
//        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
//        ps.setString(1, id);
//
//        ResultSet rs = ps.executeQuery();
//
//        User user = null;
//
//        if (rs.next()) {
//            user = new User();
//            user.setId(rs.getString("id"));
//            user.setName(rs.getString("name"));
//            user.setPassword(rs.getString("password"));
//        }
//
//        rs.close();
//        ps.close();
//        c.close();
//
//        if (user == null) {
//            throw new EmptyResultDataAccessException(1);
//        }
//
//        return user;
//    }
//
//    public void add(User user) throws SQLException {
//        Connection c = dataSource.getConnection();
//    }


    public void deleteAll() throws ClassNotFoundException, SQLException {
//        this.jdbcTemplate.update(
//                new PreparedStatementCreator() {
//                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                        return con.prepareStatement("DELETE FROM users");
//                    }
//                }
//        );

        this.jdbcTemplate.update("DELETE FROM users");
    }


//
//    public int getCount() throws ClassNotFoundException, SQLException {
//        return this.jdbcTemplate.queryForInt();
//    }
}
