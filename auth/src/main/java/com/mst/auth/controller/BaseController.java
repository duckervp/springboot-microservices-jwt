package com.mst.auth.controller;

import com.mst.auth.domain.dto.ExtendedMessageDto;
import com.mst.auth.domain.dto.MessageDto;
import org.springframework.http.HttpStatus;

public class BaseController {

    public <T> MessageDto createSuccessResponse(T data) {
        return new ExtendedMessageDto<T>(
                HttpStatus.OK.value() + "",
                true,
                null,
                null,
                data);
    }
    public <T> MessageDto createSuccessResponse(String description, String message, T data) {
        return new ExtendedMessageDto<T>(
                HttpStatus.OK.value() + "",
                true,
                description,
                message,
                data);
    }

    public MessageDto createSuccessResponse() {
        return new MessageDto(
                HttpStatus.OK.value() + "",
                true,
                null,
                null);
    }

    public MessageDto createFailureResponse(String code, String description, String message) {
        return new MessageDto(
                code,
                false,
                description,
                message);
    }
}
