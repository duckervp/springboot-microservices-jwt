package com.mst.user.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mst.user.domain.dto.MessageDto;
import com.mst.user.util.HttpServletResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    private final HttpServletResponseUtil responseUtil;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        responseUtil.createFailureResponse(request, response, HttpServletResponse.SC_BAD_REQUEST, authException);
    }
}
