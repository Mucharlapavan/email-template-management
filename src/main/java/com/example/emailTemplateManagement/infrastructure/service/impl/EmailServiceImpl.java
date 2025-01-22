package com.example.emailTemplateManagement.infrastructure.service.impl;

import com.example.emailTemplateManagement.core.dto.EmailDto;
import com.example.emailTemplateManagement.core.req.EmailReq;
import com.example.emailTemplateManagement.core.response.ApiResponse;
import com.example.emailTemplateManagement.core.response.PaginationResponse;
import com.example.emailTemplateManagement.infrastructure.model.EmailEntity;
import com.example.emailTemplateManagement.infrastructure.repository.EmailRepo;
import com.example.emailTemplateManagement.infrastructure.service.handler.EmailMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Autowired
//    private SpringTemplateEngine templateEngine;
//
//    /**
//     * Sends an email using the details provided in the UserReq object.
//     *
//     * @param userReq Object containing email details like recipients, subject, body, template name, and variables.
//     * @throws MessagingException if an error occurs during email sending.
//     */
//    public void sendEmail(UserEntity userReq) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//
//        // Generate email content if a template is specified
//        String emailContent = userReq.getBody(); // Default body
//        if (userReq.getTemplateName() != null && userReq.getTemplateModel() != null) {
//            Context context = new Context();
//            context.setVariables(userReq.getTemplateModel());
//            emailContent = templateEngine.process(userReq.getTemplateName(), context);
//        }
//
//        // Set recipients
//        if (userReq.getTo() != null && !userReq.getTo().isEmpty()) {
//            helper.setTo(userReq.getTo().toArray(new String[0]));
//        }
//
//            helper.setCc(userReq.getCc().toArray(new String[0]));
//            helper.setBcc(userReq.getBcc().toArray(new String[0]));
//
//
//        // Set subject and email content
//        helper.setSubject(userReq.getSubject());
//        helper.setText(emailContent, true); // HTML content
//
//        // Send the email
//        mailSender.send(message);
//    }


    @Autowired
  private EmailRepo emailRepo;

    @Autowired
    private EmailMapperImpl emailMapper;

    private <T> PaginationResponse<T> createPaginationResponse(Page<T> page) {
       // logger.debug("Creating pagination response for page: {}, total elements: {}", page.getNumber(), page.getTotalElements());
        return new PaginationResponse<>(
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getContent()
        );
    }

public ApiResponse createEmailTemplate(EmailReq emailReq){
    EmailEntity emailEntity=emailMapper.toEntity(emailReq.getEmailDto());
    EmailEntity saveEmailEntity=emailRepo.save(emailEntity);
    EmailDto savedEmailDto=emailMapper.toDto(saveEmailEntity);
    return new ApiResponse(true,"EmailTemplate created successfully",savedEmailDto,null);
}

}
