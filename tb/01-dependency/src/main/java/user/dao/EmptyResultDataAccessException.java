package user.dao;

import java.sql.SQLException;

public class EmptyResultDataAccessException extends SQLException {

    public EmptyResultDataAccessException(int i) {
        super();
        System.out.println(i);
    }
}
