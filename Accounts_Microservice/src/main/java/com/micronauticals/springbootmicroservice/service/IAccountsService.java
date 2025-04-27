package com.micronauticals.springbootmicroservice.service;
import com.micronauticals.springbootmicroservice.dto.CustomerDto;


public interface IAccountsService {
    /**
     * Create account.
     *
     * @param customerDto the customer dto
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetch account customer dto.
     *
     * @param mobileNumber the mobile number
     * @return the customer dto
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * Update account boolean.
     *
     * @param customerDto the customer dto
     * @return the boolean
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * Delete account boolean.
     *
     * @param mobileNumber the mobile number
     * @return the boolean
     */
    boolean deleteAccount(String mobileNumber);

    /**
     * Update communication boolean.
     *
     * @param accountNumber the mobile number
     * @return the boolean
     */

    boolean updateCommunicationStatus(Long accountNumber);
}
