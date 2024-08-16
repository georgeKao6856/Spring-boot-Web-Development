package org.practice.week3day5georgekaoquizweb.service;

import org.practice.week3day5georgekaoquizweb.dao.UserDao;
import org.practice.week3day5georgekaoquizweb.domain.User;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDao userJdbcDao;
    private UserDao userHibernateDao;

    @Qualifier("jasyptStringEncryptor")
    @Autowired
    private StringEncryptor encryptor;

    @Autowired
    public UserService(@Qualifier("userDaoJdbcImpl") UserDao userJdbcDao, @Qualifier("UserDaoHibernateImpl") UserDao userHibernateDao) {
        this.userJdbcDao = userJdbcDao;
        this.userHibernateDao = userHibernateDao;
    }

    @Transactional
    public Optional<User> validateLogin(String email, String passwd) {
        User user = userHibernateDao.getUserByEmail(email);

        String decryptedPasswd = encryptor.decrypt(user.getPasswd());

        System.out.println("input: " + passwd);
        System.out.println("Decrypted: " + decryptedPasswd);

        if(user !=null && user.getEmail().equals(email) && decryptedPasswd.equals(passwd) && user.isActive()){
            return Optional.of(user);
        }else{
            return Optional.empty();
        }
    }

    @Transactional
    public User getUserByUserId(int user_id){
        return userHibernateDao.getUserByUserId(user_id);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userHibernateDao.getAllUsers();
    }

    @Transactional
    public List<User> getAllUsersJdbc() {
        return userJdbcDao.getAllUsers();
    }

    @Transactional
    public void updateUserStatus(int user_id){
        userHibernateDao.updateUserStatus(user_id);
    }

    public User getUserByEmailJdbc(String email) {
        return userJdbcDao.getUserByEmail(email);
    }

    public void deleteUserByEmailJdbc(String email) {
        userJdbcDao.deleteUserByEmailJdbc(email);
    }

    public User getUserByUserIdJdbc(int user_id){
        return userJdbcDao.getUserByUserId(user_id);
    }
}
