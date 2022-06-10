package com.mst.auth.exception;

import javax.naming.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {
    public JwtTokenMalformedException(String explanation) {
        super(explanation);
    }
}
