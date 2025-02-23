package com.micronauticals.springbootmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class SpringBootMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMicroserviceApplication.class, args);
    }

}
