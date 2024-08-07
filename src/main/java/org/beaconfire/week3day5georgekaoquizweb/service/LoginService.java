package org.beaconfire.week3day5georgekaoquizweb.service;

import org.beaconfire.week3day5georgekaoquizweb.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UserService userService;

    public LoginService(UserService userService) {this.userService = userService;}

    public Optional<User> validateLogin(String email, String password) {
        return userService.validateLogin(email, password);
    }
}
