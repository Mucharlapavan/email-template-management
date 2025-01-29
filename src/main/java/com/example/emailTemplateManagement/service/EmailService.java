package com.example.emailTemplateManagement.service;

import com.example.emailTemplateManagement.model.EmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithAttachments(EmailTemplate emailTemplate) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // Process placeholders in the email body
        String processedBody = processPlaceholders(emailTemplate.getBody(), emailTemplate.getPlaceholders());

        // Set mandatory fields
        helper.setFrom(emailTemplate.getFromEmail());
        helper.setTo(emailTemplate.getToEmail());
        helper.setSubject(emailTemplate.getSubject());
        helper.setText(processedBody, true);

        // Add file from URL if provided
        String url = emailTemplate.getUrl(); // Assume URL is part of the EmailTemplate
        if (url != null && !url.isEmpty()) {
            try {
                // Download the file content from the URL
                InputStream inputStream = new URL(url).openStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                // Attach the downloaded file
                String fileName = "document_from_url.pdf"; // Name for the file
                helper.addAttachment(fileName, new ByteArrayDataSource(outputStream.toByteArray(), "application/pdf"));

                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
                throw new RuntimeException("Failed to download and attach file from URL: " + url, e);
            }
        }


//        // Add attachments from the List<String>
//        List<String> attachments = emailTemplate.getAttachments();
//        if (attachments != null && !attachments.isEmpty()) {
//            for (int i = 0; i < attachments.size(); i++) {
//
//                String fileContent = attachments.get(i); // Get the file content
//                String filename = "attachment" + (i + 1) + ".txt"; // Generate a filename
//                byte[] fileData = fileContent.getBytes(StandardCharsets.UTF_8);
//                helper.addAttachment(filename, new ByteArrayDataSource(fileData, "text/plain")); // Default to text/plain MIME type
//            }
//        }

        // Send the email
        mailSender.send(mimeMessage);
    }

    private String processPlaceholders(String body, Map<String, String> placeholders) {
        if (placeholders == null || placeholders.isEmpty()) {
            return body;
        }
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            String placeholder = "\\{" + entry.getKey() + "\\}";
            body = body.replaceAll(placeholder, entry.getValue());
        }
        return body;
    }
}
