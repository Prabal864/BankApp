package com.micronauticals.springbootmicroservice.controllers;
import com.micronauticals.springbootmicroservice.constants.AccountsConstants;
import com.micronauticals.springbootmicroservice.dto.CustomerDto;
import com.micronauticals.springbootmicroservice.dto.ResponseDto;
import com.micronauticals.springbootmicroservice.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {

    private IAccountsService iAccountsService;

    @PostMapping("/createAccount")
   public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto ){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, "Account created successfully"));
   }

   @GetMapping("/fetchAccountDetails")
   public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
   }

   @PutMapping("/updateAccount")
    public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto customerDto){
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
   }

   @DeleteMapping("/deleteAccount")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber){
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
   }


}
