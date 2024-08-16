package org.practice.week3day5georgekaoquizweb.dao.rowmapper;

import org.practice.week3day5georgekaoquizweb.domain.User;
import org.practice.week3day5georgekaoquizweb.domain.jdbc.UserJdbc;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User userJdbc = new UserJdbc();
        userJdbc.setUser_id(rs.getInt("user_id"));
        userJdbc.setUsername(rs.getString("username"));
        userJdbc.setFirstname(rs.getString("firstname"));
        userJdbc.setLastname(rs.getString("lastname"));
        userJdbc.setEmail(rs.getString("email"));
        userJdbc.setPasswd(rs.getString("passwd"));
        userJdbc.setAdmin(rs.getBoolean("is_admin"));
        userJdbc.setActive(rs.getBoolean("is_active"));

        return userJdbc;
    }
}
