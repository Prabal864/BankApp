package com.micronauticals.springbootmicroservice.service;
import com.micronauticals.springbootmicroservice.dto.CustomerDto;



public interface IAccountsService {
    void createAccount(CustomerDto customerDto);

}
