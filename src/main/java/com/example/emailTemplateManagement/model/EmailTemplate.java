package com.example.emailTemplateManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplate {

    private String fromEmail;
    private String toEmail;
    private String ccEmail;
    private String bccEmail;
    private String subject;
    private String body; // Contains placeholders like {name}
    private Map<String, String> placeholders; // Dynamic data for placeholders

}
