package org.practice.week3day5georgekaoquizweb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class User {
    private int user_id;
    private String username;
    private String firstname;
    private String lastname;
    private String passwd;
    private String email;
    private boolean admin;
    private boolean active;
}


