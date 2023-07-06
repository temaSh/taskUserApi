package com.example.taskuserapi.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.example.taskuserapi.repository")
@EntityScan("com.example.taskuserapi.entity")
public class H2DatabaseConfig {

    // Дополнительные настройки и бины

}

