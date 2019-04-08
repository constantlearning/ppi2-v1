package com.unitri.ppi2v1.service.exception;

import org.springframework.http.HttpStatus;

public class VeiculoAlreadyExistsException extends BusinessException {

    public VeiculoAlreadyExistsException() {
        super("veiculo.alreadyExists", HttpStatus.CONFLICT);
    }
}
