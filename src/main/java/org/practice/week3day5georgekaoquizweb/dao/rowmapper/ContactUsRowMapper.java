package org.practice.week3day5georgekaoquizweb.dao.rowmapper;

import org.practice.week3day5georgekaoquizweb.domain.ContactUs;
import org.practice.week3day5georgekaoquizweb.domain.jdbc.ContactUsJdbc;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ContactUsRowMapper implements RowMapper<ContactUs> {
    @Override
    public ContactUs mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContactUs contactUsJdbc = new ContactUsJdbc();
        contactUsJdbc.setContactus_id(rs.getInt("contactus_id"));
        contactUsJdbc.setMsg_subject(rs.getString("msg_subject"));
        contactUsJdbc.setEmail(rs.getString("email"));
        contactUsJdbc.setMessage(rs.getString("message"));
        contactUsJdbc.setSubmit_time(rs.getString("submit_time"));

        return contactUsJdbc;
    }
}
