package org.beaconfire.week3day5georgekaoquizweb.controller;

import org.beaconfire.week3day5georgekaoquizweb.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final RegisterService registerService;

    public RegistrationController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String register_username,
                               @RequestParam String register_firstname, @RequestParam String register_lastname, @RequestParam String register_email, @RequestParam String register_passwd,  Model model) {

        registerService.register(register_username,register_firstname, register_lastname, register_email, register_passwd);

        model.addAttribute("message", "Registration successful!");

        return "redirect:/login";
    }
}
