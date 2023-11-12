package com.prgrms.album_market.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.prgrms.album_market.common.exception.ErrorCode.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
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

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomerException(final CustomException ex) {
        log.error("handleCustomException", ex);
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getErrorCode()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException ex) {
        log.error("handleHttpRequestMethodNotSupportedException", ex);
        return ResponseEntity
                .status(METHOD_NOT_ALLOWED.getStatus().value())
                .body(new ErrorResponse(METHOD_NOT_ALLOWED));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception ex) {
        log.error("handleException", ex);
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
