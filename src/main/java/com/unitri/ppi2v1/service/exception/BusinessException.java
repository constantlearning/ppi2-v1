package com.unitri.ppi2v1.service.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter

public class BusinessException extends RuntimeException {

    private String code;
    private HttpStatus status;
}
