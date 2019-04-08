package com.unitri.ppi2v1.service.exception;

import org.springframework.http.HttpStatus;

public class LocacaoNotFoundException extends BusinessException {

    public LocacaoNotFoundException() {
        super("locacao.notFouund", HttpStatus.NOT_FOUND);
    }
}
