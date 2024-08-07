package org.beaconfire.week3day5georgekaoquizweb.domain.jdbc;

import lombok.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.ContactUs;
import org.springframework.jdbc.core.JdbcTemplate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContactUsJdbc extends ContactUs {
    private int contactus_id;
    private String msg_subject;
    private String email;
    private String message;
    private String submit_time;
}
