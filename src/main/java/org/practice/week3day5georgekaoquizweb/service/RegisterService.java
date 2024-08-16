package org.practice.week3day5georgekaoquizweb.service;

import org.practice.week3day5georgekaoquizweb.dao.UserDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    private UserDao userJdbcDao;
    private UserDao userHibernateDao;

    public RegisterService(@Qualifier("userDaoJdbcImpl") UserDao userJdbcDao, @Qualifier("UserDaoHibernateImpl") UserDao userHibernateDao) {
        this.userJdbcDao = userJdbcDao;
        this.userHibernateDao = userHibernateDao;
    }

    @Transactional
    public void register(String username, String firstname, String lastname, String email, String password) {
        userHibernateDao.createUser(username, firstname, lastname, email, password);
    }
}
