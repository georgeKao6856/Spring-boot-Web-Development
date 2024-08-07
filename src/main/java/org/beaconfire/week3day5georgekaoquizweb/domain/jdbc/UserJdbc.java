package org.beaconfire.week3day5georgekaoquizweb.domain.jdbc;

import lombok.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.User;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserJdbc extends User {
    private int user_id;
    private String username;
    private String firstname;
    private String lastname;
    private String passwd;
    private String email;
    private boolean admin;
    private boolean active;
}

