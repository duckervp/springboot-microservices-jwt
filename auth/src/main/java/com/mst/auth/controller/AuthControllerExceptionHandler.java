package com.mst.auth.controller;

import com.mst.auth.exception.JwtTokenMalformedException;
import com.mst.auth.exception.JwtTokenMissingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class AuthControllerExceptionHandler extends BaseController {
    @ExceptionHandler({MissingRequestHeaderException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleException(MissingRequestHeaderException e) {
        return createFailureResponse(
                HttpStatus.UNAUTHORIZED.value() + "",
                e.getMessage(),
                "Jwt token missing");
    }

    @ExceptionHandler({JwtTokenMalformedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleException(JwtTokenMalformedException e) {
        return createFailureResponse(
                HttpStatus.UNAUTHORIZED.value() + "",
                e.getMessage(),
                "Jwt token malformed");
    }

    @ExceptionHandler({JwtTokenMissingException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleException(JwtTokenMissingException e) {
        return createFailureResponse(
                HttpStatus.UNAUTHORIZED.value() + "",
                e.getMessage(),
                "Empty jwt token");
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException e) {
        return createFailureResponse(
                HttpStatus.BAD_REQUEST.value() + "",
                Objects.requireNonNull(e.getFieldError()).getDefaultMessage(),
                null);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleException(IllegalArgumentException e) {
        return createFailureResponse(
                HttpStatus.BAD_REQUEST.value() + "",
                e.getMessage(),
                null);
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleException(Exception e) {
        return createFailureResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value() + "",
                e.getMessage(),
                null);
    }
}
