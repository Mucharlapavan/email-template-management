package com.example.emailTemplateManagement.service;

import com.example.emailTemplateManagement.model.EmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(EmailTemplate emailTemplate) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // Replace placeholders in the email body
        String processedBody = processPlaceholders(emailTemplate.getBody(), emailTemplate.getPlaceholders());

        // Set mandatory fields
        helper.setFrom(emailTemplate.getFromEmail());
        helper.setTo(emailTemplate.getToEmail());
        helper.setSubject(emailTemplate.getSubject());
        helper.setText(processedBody, true); // HTML support

        // Add optional fields (CC and BCC)
        if (emailTemplate.getCcEmail() != null && !emailTemplate.getCcEmail().isEmpty()) {
            helper.setCc(emailTemplate.getCcEmail());
        }
        if (emailTemplate.getBccEmail() != null && !emailTemplate.getBccEmail().isEmpty()) {
            helper.setBcc(emailTemplate.getBccEmail());
        }

        // Send the email
        mailSender.send(mimeMessage);
    }

    private String processPlaceholders(String body, Map<String, String> placeholders) {
        if (placeholders == null || placeholders.isEmpty()) {
            return body;
        }
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            String placeholder = "\\{" + entry.getKey() + "\\}"; // Match {key}
            body = body.replaceAll(placeholder, entry.getValue());
        }
        return body;
    }
}