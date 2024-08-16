package org.practice.week3day5georgekaoquizweb.controller;

import org.practice.week3day5georgekaoquizweb.service.ContactUsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactUsController {

    private final ContactUsService contactUsService;

    public ContactUsController(ContactUsService contactUsService) {this.contactUsService = contactUsService;}

    @GetMapping("/contactus")
    public String contactUs(Model model) {
        return "contactus";
    }

    @PostMapping("/contactus")
    public String CreateContactUs(@RequestParam String msg_subject, @RequestParam String email, @RequestParam String message, Model model) {
        contactUsService.createContactUs(msg_subject, email, message);

        model.addAttribute("messgae", "Successful create a contact us");

        return "/contactus";
    }
}
