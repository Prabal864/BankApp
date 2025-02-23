package com.micronauticals.springbootmicroservice.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AccountsDto {

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number is invalid")
    @NotEmpty(message = "Mobile number is required")
    private Long accountNumber;

    @NotEmpty(message = "Account type is required")
    private String accountType;

    @NotEmpty(message = "Branch address is required")
    private String branchAddress;
}
