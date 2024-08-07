package org.beaconfire.week3day5georgekaoquizweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.beaconfire.week3day5georgekaoquizweb.domain.User;
import org.beaconfire.week3day5georgekaoquizweb.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/quiz";
        }

        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String login_email,
                            @RequestParam String login_passwd,
                            HttpServletRequest request, Model model) {
        Optional<User> possibleUser = loginService.validateLogin(login_email, login_passwd);

        if (possibleUser.isPresent()) {
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) oldSession.invalidate();
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("user", possibleUser.get());

            return "redirect:/home";
        }else{
            model.addAttribute("error_msg", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession oldSession = request.getSession(false);
        // invalidate old session if it exists
        if(oldSession != null) oldSession.invalidate();
        return "redirect:/login";
    }
}
