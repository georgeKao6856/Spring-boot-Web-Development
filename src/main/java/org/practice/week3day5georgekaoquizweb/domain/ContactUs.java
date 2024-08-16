package org.practice.week3day5georgekaoquizweb.domain;

import lombok.*;

@Getter
@Setter
@ToString
public abstract class ContactUs {
    private int contactus_id;
    private String msg_subject;
    private String email;
    private String message;
    private String submit_time;
}
