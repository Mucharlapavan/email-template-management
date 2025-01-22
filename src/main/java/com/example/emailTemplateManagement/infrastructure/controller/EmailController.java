package com.example.emailTemplateManagement.infrastructure.controller;

import com.example.emailTemplateManagement.core.req.EmailReq;
import com.example.emailTemplateManagement.core.response.ApiResponse;
import com.example.emailTemplateManagement.infrastructure.model.EmailEntity;
import com.example.emailTemplateManagement.infrastructure.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email-templates")
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

//    @PostMapping("/send")
//    public String sendEmail(@RequestBody EmailEntity userReq) {
//        try {
//            emailService.sendEmail(userReq);
//            return "Email sent successfully!";
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return "Failed to send email: " + e.getMessage();
//        }
//    }


    @PostMapping
    public ResponseEntity<ApiResponse> createEmailTemplate(@RequestBody EmailReq emailReq) {
        ApiResponse response = emailService.createEmailTemplate(emailReq);
        return ResponseEntity.ok(response);
    }
}