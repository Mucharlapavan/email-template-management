package com.example.emailTemplateManagement.infrastructure.service.handler;

import com.example.emailTemplateManagement.core.dto.EmailDto;
import com.example.emailTemplateManagement.infrastructure.model.EmailEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailMapperImpl implements EmailMapper{

    @Autowired
    ModelMapper modelMapper;
    @Override
    public EmailEntity toEntity(EmailDto emailDto) {
        EmailEntity emailEntity=modelMapper.map(emailDto, EmailEntity.class);
        return emailEntity;
    }

    @Override
    public EmailDto toDto(EmailEntity emailEntity) {
        EmailDto emailDto=modelMapper.map(emailEntity, EmailDto.class);
        return emailDto;
    }
}
