package com.prgrms.album_market.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
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
    protected ResponseEntity<ErrorResponse> handleNotFound(CustomException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getErrorCode()));
    }
}
