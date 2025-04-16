package com.micronauticals.loansmicroservice;

import com.micronauticals.loansmicroservice.dto.LoansContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
<<<<<<< HEAD:V2_Cloud_Config/LoansMicroservice/src/main/java/com/micronauticals/loansmicroservice/LoansmicroserviceApplication.java
=======
import org.springframework.cloud.openfeign.EnableFeignClients;
>>>>>>> Logging_Monitoring:LoansMicroservice/src/main/java/com/micronauticals/loansmicroservice/LoansmicroserviceApplication.java
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
