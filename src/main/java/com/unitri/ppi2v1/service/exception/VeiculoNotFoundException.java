package com.unitri.ppi2v1.service.exception;

import org.springframework.http.HttpStatus;

public class VeiculoNotFoundException extends BusinessException {

    public VeiculoNotFoundException() {
        super("veiculo.notFound", HttpStatus.NOT_FOUND);
    }
}
