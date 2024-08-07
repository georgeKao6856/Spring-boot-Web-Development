package org.beaconfire.week3day5georgekaoquizweb.dao.jdbc;

import org.beaconfire.week3day5georgekaoquizweb.dao.ContactUsDao;
import org.beaconfire.week3day5georgekaoquizweb.domain.ContactUs;
import org.beaconfire.week3day5georgekaoquizweb.dao.rowmapper.ContactUsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactUsDaoJdbcImpl implements ContactUsDao {
    private JdbcTemplate jdbcTemplate;
    private ContactUsRowMapper rowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ContactUsDaoJdbcImpl(JdbcTemplate jdbcTemplate, ContactUsRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createContactUs (String msg_subject, String email, String message) {
        try{
            String query = "Insert Into ContactUsMessage (msg_subject, email, message) values (?, ?, ?)";
            jdbcTemplate.update(query, msg_subject, email, message);
            System.out.println("Successfully created a contactus");
        }catch(DataAccessException ex){
            System.err.println("Error occurred: " + ex.getMessage());
        }
    }

    public List<ContactUs> getAllContactUs () {
        String query = "Select * from ContactUsMessage order by submit_time desc";
        return jdbcTemplate.query(query, rowMapper);
    }
}
