package com.example.emailTemplateManagement.infrastructure.service.handler;

import com.example.emailTemplateManagement.core.dto.EmailDto;
import com.example.emailTemplateManagement.infrastructure.model.EmailEntity;

public interface EmailMapper {

    EmailEntity toEntity(EmailDto emailDto);
    EmailDto toDto(EmailEntity emailEntity);
}
