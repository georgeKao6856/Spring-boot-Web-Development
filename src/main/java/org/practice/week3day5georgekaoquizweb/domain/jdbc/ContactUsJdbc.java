package org.practice.week3day5georgekaoquizweb.domain.jdbc;

import lombok.*;
import org.practice.week3day5georgekaoquizweb.domain.ContactUs;

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
