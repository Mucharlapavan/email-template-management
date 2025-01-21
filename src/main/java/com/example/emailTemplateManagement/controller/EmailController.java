package com.example.emailTemplateManagement.controller;

import com.example.emailTemplateManagement.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-welcome")
    public String sendWelcomeEmail() {
        String to = "m94pavan@gmail.com";
        String subject = "Welcome to Our Platform!";
        String templateName = "welcome-email";

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("name", "John Doe");
        templateModel.put("loginUrl", "https://yourdomain.com/login");

        try {
            emailService.sendEmail(to, subject, templateName, templateModel);
            return "Email sent successfully!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending email: " + e.getMessage();
        }
    }
}
