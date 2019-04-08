package com.unitri.ppi2v1.service.exception;

import org.springframework.http.HttpStatus;

public class FuncionarioNotFoundException extends BusinessException {

    public FuncionarioNotFoundException() {
        super("funcionario.notFound", HttpStatus.NOT_FOUND);
    }
}
