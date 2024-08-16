package org.practice.week3day5georgekaoquizweb.service;

import org.practice.week3day5georgekaoquizweb.dao.ContactUsDao;
import org.practice.week3day5georgekaoquizweb.domain.ContactUs;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactUsService {

    private ContactUsDao contactUsJdbcDao;
    private ContactUsDao contactUsHibernateDao;

    public ContactUsService(@Qualifier("contactUsDaoJdbcImpl") ContactUsDao contactUsJdbcDao, @Qualifier("ContactUsDaoHibernateImpl") ContactUsDao contactUsHibernateDao) {
        this.contactUsJdbcDao = contactUsJdbcDao;
        this.contactUsHibernateDao = contactUsHibernateDao;
    }

    @Transactional
    public void createContactUs(String msg_subject, String email, String message) {
        contactUsHibernateDao.createContactUs(msg_subject, email, message);
    }

    @Transactional
    public List<ContactUs> getAllContactUs () {
        return contactUsHibernateDao.getAllContactUs();
    }
}
