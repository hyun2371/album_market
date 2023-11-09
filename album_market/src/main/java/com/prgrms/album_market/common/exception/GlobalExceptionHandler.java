package com.prgrms.album_market.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<List<ErrorResponse>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        for (FieldError x : ex.getBindingResult()
                .getFieldErrors()) {
            String defaultMessage = x.getDefaultMessage();
            errorResponses.add(new ErrorResponse(ErrorCode.matchErrorCode(defaultMessage)));
        }
        return ResponseEntity
                .badRequest()
                .body(errorResponses);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleNotFound(CustomException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getErrorCode()));
    }
}
