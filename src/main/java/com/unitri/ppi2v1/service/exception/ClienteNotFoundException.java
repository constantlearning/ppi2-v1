package com.unitri.ppi2v1.service.exception;

import org.springframework.http.HttpStatus;

public class ClienteNotFoundException extends BusinessException {

    public ClienteNotFoundException() {
        super("cliente.notFound", HttpStatus.NOT_FOUND);
    }
}
