package com.mst.user.exception;

import javax.naming.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {
    public JwtTokenMissingException(String explanation) {
        super(explanation);
    }
}
