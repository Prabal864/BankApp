package com.micronauticals.springbootmicroservice.impl;

import com.micronauticals.springbootmicroservice.dto.AccountsDto;
import com.micronauticals.springbootmicroservice.dto.CardsDto;
import com.micronauticals.springbootmicroservice.dto.CustomerDetailsDto;
import com.micronauticals.springbootmicroservice.dto.LoansDto;
import com.micronauticals.springbootmicroservice.entity.Accounts;
import com.micronauticals.springbootmicroservice.entity.Customer;
import com.micronauticals.springbootmicroservice.exceptions.ResourceNotFoundException;
import com.micronauticals.springbootmicroservice.mapper.AccountsMapper;
import com.micronauticals.springbootmicroservice.mapper.CustomerMapper;
import com.micronauticals.springbootmicroservice.repository.AccountsRepository;
import com.micronauticals.springbootmicroservice.repository.CustomerRepository;
import com.micronauticals.springbootmicroservice.service.ICustomerService;
import com.micronauticals.springbootmicroservice.service.client.CardsFeignClient;
import com.micronauticals.springbootmicroservice.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        if(null!=loansDtoResponseEntity){
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
        if(null!=cardsDtoResponseEntity){
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerDetailsDto;

    }

}
