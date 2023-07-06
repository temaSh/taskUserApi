package com.example.taskuserapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.taskuserapi.repository")
public class JpaConfig {
    // Здесь можно добавить дополнительную конфигурацию JPA, если необходимо
}

