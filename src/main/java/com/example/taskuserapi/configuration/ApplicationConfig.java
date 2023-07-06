package com.example.taskuserapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.example.taskuserapi.repository")
public class ApplicationConfig {

    // Можно добавить дополнительные настройки и бины

}
