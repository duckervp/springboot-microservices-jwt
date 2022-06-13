package com.mst.auth.domain.model;

import com.mst.auth.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthOutput {
    private String accessToken;
}
