package com.mst.auth.controller;

import com.mst.auth.domain.dto.MessageDto;
import com.mst.auth.exception.JwtTokenMalformedException;
import com.mst.auth.exception.JwtTokenMissingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuthExceptionHandler extends BaseController {
    @ExceptionHandler({MissingRequestHeaderException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public MessageDto handleException(MissingRequestHeaderException e) {
        return createFailureResponse(
                HttpStatus.UNAUTHORIZED.toString(),
                "Jwt token missing",
                e.getMessage());
    }

    @ExceptionHandler({JwtTokenMalformedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public MessageDto handleException(JwtTokenMalformedException e) {
        return createFailureResponse(
                HttpStatus.UNAUTHORIZED.toString(),
                "Jwt token malformed",
                e.getMessage());
    }

    @ExceptionHandler({JwtTokenMissingException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public MessageDto handleException(JwtTokenMissingException e) {
        return createFailureResponse(
                HttpStatus.UNAUTHORIZED.toString(),
                "Empty jwt token",
                e.getMessage());
    }
}
