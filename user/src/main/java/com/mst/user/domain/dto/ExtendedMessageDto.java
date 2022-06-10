package com.mst.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedMessageDto<T> extends MessageDto {
    private T data;

    public ExtendedMessageDto(String code, Boolean status, String description, String message, T data) {
        super(code, status, description, message);
        this.data = data;
    }
}
