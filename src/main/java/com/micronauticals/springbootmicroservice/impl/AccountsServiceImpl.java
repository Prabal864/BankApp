package com.micronauticals.springbootmicroservice.impl;
import com.micronauticals.springbootmicroservice.constants.AccountsConstants;
import com.micronauticals.springbootmicroservice.dto.CustomerDto;
import com.micronauticals.springbootmicroservice.entity.Accounts;
import com.micronauticals.springbootmicroservice.entity.Customer;
import com.micronauticals.springbootmicroservice.exceptions.CustomerAlreadyExistsException;
import com.micronauticals.springbootmicroservice.mapper.CustomerMapper;
import com.micronauticals.springbootmicroservice.repository.AccountsRepository;
import com.micronauticals.springbootmicroservice.repository.CustomerRepository;
import com.micronauticals.springbootmicroservice.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    private Accounts createdNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setAccountNumber(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }


    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number: "+customerDto.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createdNewAccount(savedCustomer));
    }


}
