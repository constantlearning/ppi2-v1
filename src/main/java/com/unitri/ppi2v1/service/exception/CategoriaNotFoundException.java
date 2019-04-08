package com.unitri.ppi2v1.service.exception;

import org.springframework.http.HttpStatus;

public class CategoriaNotFoundException extends BusinessException {

    public CategoriaNotFoundException() {
        super("cateogia.notFound", HttpStatus.NOT_FOUND);
    }
}
