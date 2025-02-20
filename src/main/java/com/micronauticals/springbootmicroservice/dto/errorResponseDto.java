package com.micronauticals.springbootmicroservice.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class errorResponseDto {
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
}
