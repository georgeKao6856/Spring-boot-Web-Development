package org.beaconfire.week3day5georgekaoquizweb.service;

import org.beaconfire.week3day5georgekaoquizweb.dao.UserDao;
import org.beaconfire.week3day5georgekaoquizweb.domain.User;
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

    @Autowired
    public UserService(@Qualifier("userDaoJdbcImpl") UserDao userJdbcDao, @Qualifier("UserDaoHibernateImpl") UserDao userHibernateDao) {
        this.userJdbcDao = userJdbcDao;
        this.userHibernateDao = userHibernateDao;
    }

    @Transactional
    public Optional<User> validateLogin(String email, String passwd) {
        User user = userHibernateDao.getUserByEmail(email);

        if(user !=null && user.getEmail().equals(email) && user.getPasswd().equals(passwd) && user.isActive()){
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
    public void updateUserStatus(int user_id){
        userHibernateDao.updateUserStatus(user_id);
    }
}
