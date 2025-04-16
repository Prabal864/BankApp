package com.micronauticals.springbootmicroservice.dto;
import jakarta.validation.constraints.*;
import lombok.Data;


/**
 * The type Customer dto.
 */
@Data
public class CustomerDto {

    @NotEmpty(message = "Name is required")
    @Size(min=5,max=30, message = "Name should be between 5 to 30 characters")
    private String name;

    @NotNull(message = "Email is required")
    @Email
    private String email;

    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number is invalid")
    private String mobileNumber;

    private AccountsDto accountsDto;

}
