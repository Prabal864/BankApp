package com.micronauticals.loansmicroservice;

import com.micronauticals.loansmicroservice.dto.LoansContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(LoansContactInfoDto.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class LoansmicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansmicroserviceApplication.class, args);
    }

}
