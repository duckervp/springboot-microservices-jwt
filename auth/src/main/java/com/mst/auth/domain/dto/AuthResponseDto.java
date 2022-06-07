package com.mst.auth.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private final String tokenType = "Bearer";
    private String accessToken;
    private UserDto userInfo;
}
