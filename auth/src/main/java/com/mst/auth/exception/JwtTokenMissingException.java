package com.mst.auth.exception;

import javax.naming.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {
    public JwtTokenMissingException(String explanation) {
        super(explanation);
    }
}
