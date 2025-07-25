package com.micronauticals.springbootmicroservice.functions;

import com.micronauticals.springbootmicroservice.service.IAccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {

    private static final Logger log = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountsService iAccountsService){
        return accountNumber -> {
            log.info("Updating communication for account number: {}", accountNumber.toString());
            iAccountsService.updateCommunicationStatus(accountNumber);
        };
    }


}
