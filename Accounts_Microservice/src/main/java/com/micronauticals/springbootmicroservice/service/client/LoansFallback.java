package com.micronauticals.springbootmicroservice.service.client;

import com.micronauticals.springbootmicroservice.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class LoansFallback implements LoansFeignClient {

    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails( String correlationId, String mobileNumber){
        return null;
    }


}
