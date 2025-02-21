package com.micronauticals.springbootmicroservice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resrouceName, String fieldName, String message) {
        super(String.format("%s not found with %s : '%s'", resrouceName, fieldName, message));
    }
}
