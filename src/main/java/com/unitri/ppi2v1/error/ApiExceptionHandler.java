package com.unitri.ppi2v1.error;


import com.unitri.ppi2v1.error.ErrorResponse.ApiError;
import com.unitri.ppi2v1.service.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor

@ControllerAdvice
public class ApiExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);
    private final String NO_MESSAGE_AVAILABLE = "No message available.";

    private final MessageSource apiErrorMessageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception, Locale locale) {
        LOGGER.error("error not expected: ", exception);
        final ErrorCode errorCode = createErrorCode("internalServerError", HttpStatus.INTERNAL_SERVER_ERROR);
        final ErrorResponse errorResponse = ErrorResponse.of(errorCode.getStatus(), toApiError(errorCode, locale));
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception, Locale locale) {
        final ErrorCode errorCode = createErrorCode(exception.getCode(), exception.getStatus());
        final ErrorResponse errorResponse = ErrorResponse.of(errorCode.getStatus(), toApiError(errorCode, locale));
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception, Locale locale) {

        Stream<ObjectError> errors = exception.getBindingResult().getAllErrors().stream();

        final List<ApiError> apiErrors = errors
                .map(ObjectError::getDefaultMessage)
                .map(this::createErrorCode)
                .map(code -> toApiError(code, locale))
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(ErrorResponse.ofErrors(HttpStatus.BAD_REQUEST, apiErrors));
    }

    private ApiError toApiError(ErrorCode errorCode, Locale locale, Object... args) {

        String message;

        try {
            message = this.apiErrorMessageSource.getMessage(errorCode.getCode(), args, locale);
        } catch (NoSuchMessageException exception) {
            LOGGER.error("Couldn't find any message for {} code under locale {}", errorCode.getCode(), locale);
            message = NO_MESSAGE_AVAILABLE;
        }

        return new ApiError(errorCode.getCode(), message);
    }

    private ErrorCode createErrorCode(String errorCode, HttpStatus status) {
        return new ErrorCode(errorCode, status);
    }

    private ErrorCode createErrorCode(String errorCode) {
        return new ErrorCode(errorCode, HttpStatus.BAD_REQUEST);
    }
}
