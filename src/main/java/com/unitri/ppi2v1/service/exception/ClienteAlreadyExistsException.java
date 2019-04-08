package com.unitri.ppi2v1.service.exception;

import org.springframework.http.HttpStatus;

public class ClienteAlreadyExistsException extends BusinessException {

    public ClienteAlreadyExistsException() {
        super("cliente.alreadyExist", HttpStatus.CONFLICT);
    }
}
