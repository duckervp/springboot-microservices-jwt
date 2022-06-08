package com.mst.auth.controller;

import com.mst.auth.domain.dto.LoginRequestDto;
import com.mst.auth.domain.dto.AuthResponseDto;
import com.mst.auth.domain.dto.RegisterRequestDto;
import com.mst.auth.domain.dto.UserDto;
import com.mst.auth.domain.entity.Role;
import com.mst.auth.domain.entity.User;
import com.mst.auth.domain.model.CustomUserDetails;
import com.mst.auth.service.IRoleService;
import com.mst.auth.service.IUserService;
import com.mst.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;
    private final IUserService userService;

    private final IRoleService roleService;

    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody @Validated @Valid LoginRequestDto loginInfo) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails.getUsername());

        UserDto user = modelMapper.map(userDetails, UserDto.class);

        return new AuthResponseDto(token, user);
    }

    @PostMapping("/register")
    public String register(@RequestBody @Validated @Valid RegisterRequestDto registerInfo) {
        if (userService.existsByUsername(registerInfo.getUsername())) {
            return "Username already been taken!";
        }
        Set<Role> roles = registerInfo.getRoleIds().stream()
                .map(id -> roleService.findById(id))
                        .collect(Collectors.toSet());

        User user = new User(
                registerInfo.getName(),
                registerInfo.getUsername(),
                passwordEncoder.encode(registerInfo.getPassword()),
                registerInfo.getMajorId(),
                roles
        );
        user = userService.save(user);
        return Objects.nonNull(user.getId()) ? "Register success!" : "Register failed!";
    }
}
