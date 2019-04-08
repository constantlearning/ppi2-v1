package com.unitri.ppi2v1.service.exception;

import org.springframework.http.HttpStatus;

public class MultaAlreadyExistsException extends BusinessException {

    public MultaAlreadyExistsException() {
        super("multa.alreadyExists", HttpStatus.CONFLICT);
    }
}
