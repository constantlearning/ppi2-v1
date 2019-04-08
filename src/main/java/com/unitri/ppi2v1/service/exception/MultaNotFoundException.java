package com.unitri.ppi2v1.service.exception;

import org.springframework.http.HttpStatus;

public class MultaNotFoundException extends BusinessException {

    public MultaNotFoundException() {
        super("multa.notFound", HttpStatus.NOT_FOUND);
    }
}
