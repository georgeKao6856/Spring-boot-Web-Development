package org.practice.week3day5georgekaoquizweb.dao;

import org.practice.week3day5georgekaoquizweb.domain.ContactUs;

import java.util.List;

public interface ContactUsDao {

    void createContactUs (String msg_subject, String email, String message);

    List<ContactUs> getAllContactUs ();
}
