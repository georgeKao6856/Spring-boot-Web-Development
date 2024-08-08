package org.beaconfire.week3day5georgekaoquizweb.dao;

import org.beaconfire.week3day5georgekaoquizweb.domain.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    User getUserByUserName(String userName);
    User getUserByUserId(int user_id);
    User getUserByEmail(String email);
    void createUser(String username, String firstname, String lastname, String email, String passwd);
    void updateUserStatus(int user_id);
    void deleteUserByEmailJdbc(String email);
}
