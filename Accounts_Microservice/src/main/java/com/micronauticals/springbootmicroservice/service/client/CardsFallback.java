package com.micronauticals.springbootmicroservice.service.client;

import com.micronauticals.springbootmicroservice.dto.CardsDto;
import com.micronauticals.springbootmicroservice.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient {

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber){
        return null;
    }

}
