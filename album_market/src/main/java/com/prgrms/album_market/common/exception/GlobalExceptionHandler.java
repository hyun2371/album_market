package com.prgrms.album_market.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentNotValidException", ex);
        StringBuilder sb = new StringBuilder();
        for (FieldError e : ex.getBindingResult().getFieldErrors()) {
            sb.append(e.getDefaultMessage());
            sb.append(", ");
        }
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(sb.toString()));
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleCustomerException(CustomException ex) {
        log.error("handleCustomException", ex);
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getErrorCode()));
    }
}
