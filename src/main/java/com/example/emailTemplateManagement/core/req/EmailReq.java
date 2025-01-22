package com.example.emailTemplateManagement.core.req;

import com.example.emailTemplateManagement.core.dto.EmailDto;
import lombok.Data;

@Data
public class EmailReq {
    private EmailDto emailDto=new EmailDto();
}
