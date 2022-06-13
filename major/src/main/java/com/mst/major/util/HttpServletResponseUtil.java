package com.mst.major.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mst.major.domain.message.BaseMessage;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Component
public class HttpServletResponseUtil {
    public void createFailureResponse(HttpServletRequest request, HttpServletResponse response, int statusCode, Exception e) throws IOException {
        BaseMessage message = new BaseMessage(
                statusCode + "",
                false,
                request.getServletPath(),
                e.getMessage()
        );
        sendFailureResponse(response, message);
    }

    public void sendFailureResponse(HttpServletResponse response, BaseMessage message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON);
        response.setStatus(Integer.valueOf(message.getCode()));
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), message);
    }
}
