package org.beaconfire.week3day5georgekaoquizweb.dao.jdbc;

import org.beaconfire.week3day5georgekaoquizweb.dao.UserDao;
import org.beaconfire.week3day5georgekaoquizweb.domain.User;
import org.beaconfire.week3day5georgekaoquizweb.dao.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoJdbcImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private UserRowMapper rowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDaoJdbcImpl(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM Users";

        List<User> users = jdbcTemplate.query(query, rowMapper);

        return users;
    }

    public User getUserByUserName(String userName) {
        String query = "SELECT * FROM Users WHERE username = ?";
        User user = jdbcTemplate.queryForObject(query, rowMapper, userName);
        return user;
    }

    public User getUserByUserId(int user_id) {
        String query = "SELECT * FROM Users WHERE user_id = ?";
        User user = jdbcTemplate.queryForObject(query, rowMapper, user_id);
        return user;
    }

    public User getUserByEmail(String email) {
        try{
            String query = "SELECT * FROM Users WHERE email = ?";
            User user = jdbcTemplate.queryForObject(query, rowMapper, email);
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void createUser(String username, String firstname, String lastname, String email, String passwd) {
        try{
            String query = "INSERT INTO Users (username, firstname, lastname, email, passwd) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(query, username, firstname, lastname, email, passwd);
            System.out.println("Successfully created user");
        }catch (DataAccessException ex){
            System.err.println("Error occurred: " + ex.getMessage());
        }
    }

    public void updateUserStatus(int user_id){
        String query = "UPDATE Users SET is_active = ? WHERE user_id = ?";
        User user = getUserByUserId(user_id);
        if(user.isActive()){
            jdbcTemplate.update(query, false, user_id);
        }else{
            jdbcTemplate.update(query, true, user_id);
        }

        System.out.println("Successfully updated user status");
    }

    public void deleteUserByEmailJdbc(String email){
        String query = "DELETE FROM Users WHERE email = ?";
        jdbcTemplate.update(query, email);
    }

}
