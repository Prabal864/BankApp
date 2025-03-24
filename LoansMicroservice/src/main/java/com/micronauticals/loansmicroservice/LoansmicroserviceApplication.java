package com.micronauticals.loansmicroservice;

import com.micronauticals.loansmicroservice.dto.LoansContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@EnableConfigurationProperties(value = LoansContactInfoDto.class)
public class LoansmicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansmicroserviceApplication.class, args);
    }

}
