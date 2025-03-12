package com.micronauticals.springbootmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AccountsMicroservice {

    public static void main(String[] args) {
        SpringApplication.run(AccountsMicroservice.class, args);
    }

}
