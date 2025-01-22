package com.example.emailTemplateManagement.infrastructure.repository;

import com.example.emailTemplateManagement.infrastructure.model.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepo extends JpaRepository<EmailEntity, Long> {
}
