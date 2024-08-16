package org.practice.week3day5georgekaoquizweb.dao.hibernate;

import org.practice.week3day5georgekaoquizweb.dao.AbstractHibernateDao;
import org.practice.week3day5georgekaoquizweb.dao.ContactUsDao;
import org.practice.week3day5georgekaoquizweb.domain.ContactUs;
import org.practice.week3day5georgekaoquizweb.domain.hibernate.ContactUsHibernate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("ContactUsDaoHibernateImpl")
public class ContactUsDaoHibernateImpl extends AbstractHibernateDao<ContactUsHibernate> implements ContactUsDao {

    public ContactUsDaoHibernateImpl() {setClazz(ContactUsHibernate.class);}

    public void createContactUs (String msg_subject, String email, String message) {
        ContactUsHibernate contactus = ContactUsHibernate.builder()
                .msg_subject(msg_subject)
                .email(email)
                .message(message)
                .build();

        add(contactus);
    }

    public List<ContactUs> getAllContactUs () {
        List<ContactUsHibernate> contactUses = this.getAll();

        return contactUses.stream().map(contactus -> (ContactUs) contactus).collect(Collectors.toList());
    }
}
