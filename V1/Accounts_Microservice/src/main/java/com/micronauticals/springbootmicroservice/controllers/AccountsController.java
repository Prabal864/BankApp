package com.micronauticals.springbootmicroservice.controllers;
import com.micronauticals.springbootmicroservice.constants.AccountsConstants;
import com.micronauticals.springbootmicroservice.dto.AccountsContactInfoDto;
import com.micronauticals.springbootmicroservice.dto.CustomerDto;
import com.micronauticals.springbootmicroservice.dto.ResponseDto;
import com.micronauticals.springbootmicroservice.service.IAccountsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

    private final IAccountsService iAccountsService;

    private final Environment environment;

    private final AccountsContactInfoDto accountsContactInfoDto;

    public AccountsController(IAccountsService iAccountsService, Environment environment, AccountsContactInfoDto accountsContactInfoDto) {
        this.iAccountsService = iAccountsService;
        this.environment = environment;
        this.accountsContactInfoDto = accountsContactInfoDto;
    }

    @Value("${build.version}")
    private String buildVersion;

    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, "Account created successfully"));
    }

    @GetMapping("/fetchAccountDetails")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping("/updateAccount")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                     String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("/buildInfo")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);

    }

    @GetMapping("/environment")
    public ResponseEntity<String> getEnvironment() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("MAVEN_HOME"));
    }

    @GetMapping("/contact")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }

}
