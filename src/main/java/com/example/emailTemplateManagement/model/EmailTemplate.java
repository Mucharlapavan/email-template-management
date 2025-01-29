package com.example.emailTemplateManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplate {

    private String fromEmail; // Sender's email
    private String toEmail;   // Recipient's email
    private String subject;   // Email subject
    private String body;      // Email body (HTML or plain text)
    private String ccEmail;   // Optional CC
    private String bccEmail;  // Optional BCC
    private Map<String, String> placeholders; // Key-value pairs for placeholders in the email body
    private List<String> attachments;
    private String url; // New property for the file URL


}
