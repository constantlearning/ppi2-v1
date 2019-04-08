package com.unitri.ppi2v1.service.exception;

import org.springframework.http.HttpStatus;

public class LocacaoAlreadyExistException extends BusinessException {

    public LocacaoAlreadyExistException() {
        super("locacao.AlreadyExist", HttpStatus.CONFLICT);
    }
}
