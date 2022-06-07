package com.mst.auth.controller;

import com.mst.auth.domain.dto.AuthRequestDto;
import com.mst.auth.domain.dto.AuthResponseDto;
import com.mst.auth.domain.dto.UserDto;
import com.mst.auth.domain.model.CustomUserDetails;
import com.mst.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtUtil jwtUtil;

    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto authInfo) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authInfo.getUsername(), authInfo.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails.getUsername());

        UserDto user = modelMapper.map(userDetails, UserDto.class);

        return new AuthResponseDto(token, user);
    }
}
