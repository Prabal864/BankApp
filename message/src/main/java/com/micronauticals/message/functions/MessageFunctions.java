package com.micronauticals.message.functions;

import com.micronauticals.message.dto.AccountsMsgDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    @Bean
    public Function<AccountsMsgDto, AccountsMsgDto> email(){
        return accountsMsgDto -> {
            System.out.println("Email Function Invoked " + accountsMsgDto);
            return accountsMsgDto;
        };
    }

    @Bean
    public Function<AccountsMsgDto, Long> sms(){
        return accountsMsgDto -> {
            System.out.println("SMS Function Invoked " + accountsMsgDto);
            return accountsMsgDto.accountNumber();
        };
    }


}
