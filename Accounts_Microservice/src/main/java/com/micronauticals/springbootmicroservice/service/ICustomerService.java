package com.micronauticals.springbootmicroservice.service;

import com.micronauticals.springbootmicroservice.dto.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
