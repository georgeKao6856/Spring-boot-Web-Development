package org.beaconfire.week3day5georgekaoquizweb.dao;

import org.beaconfire.week3day5georgekaoquizweb.domain.ContactUs;
import org.beaconfire.week3day5georgekaoquizweb.domain.jdbc.ContactUsJdbc;

import java.util.List;

public interface ContactUsDao {

    void createContactUs (String msg_subject, String email, String message);

    List<ContactUs> getAllContactUs ();
}
