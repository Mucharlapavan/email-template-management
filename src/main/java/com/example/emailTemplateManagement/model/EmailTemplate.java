package com.example.emailTemplateManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplate {

    private String templateName;
    private String fromEmail;
    private String toEmail;
    private String ccEmail;  // Optional
    private String bccEmail; // Optional
    private String subject;
    private String body;
    private String triggerEvent;
}
