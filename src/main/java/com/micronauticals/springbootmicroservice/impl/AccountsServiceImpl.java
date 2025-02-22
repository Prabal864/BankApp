package com.micronauticals.springbootmicroservice.impl;
import com.micronauticals.springbootmicroservice.constants.AccountsConstants;
import com.micronauticals.springbootmicroservice.dto.AccountsDto;
import com.micronauticals.springbootmicroservice.dto.CustomerDto;
import com.micronauticals.springbootmicroservice.entity.Accounts;
import com.micronauticals.springbootmicroservice.entity.Customer;
import com.micronauticals.springbootmicroservice.exceptions.CustomerAlreadyExistsException;
import com.micronauticals.springbootmicroservice.exceptions.ResourceNotFoundException;
import com.micronauticals.springbootmicroservice.mapper.AccountsMapper;
import com.micronauticals.springbootmicroservice.mapper.CustomerMapper;
import com.micronauticals.springbootmicroservice.repository.AccountsRepository;
import com.micronauticals.springbootmicroservice.repository.CustomerRepository;
import com.micronauticals.springbootmicroservice.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    private Accounts createdNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("PPS");
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
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("PPS");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createdNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

        return customerDto;
    }

    /**
     * @param customerDto the customer dto
     * @return boolean
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null){
            Accounts account = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, account);
            account = accountsRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;

        }
        return false;
     }

}
