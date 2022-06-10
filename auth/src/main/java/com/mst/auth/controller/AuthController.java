package com.mst.auth.controller;

import com.mst.auth.domain.dto.*;
import com.mst.auth.domain.entity.Role;
import com.mst.auth.domain.entity.User;
import com.mst.auth.domain.model.CustomUserDetails;
import com.mst.auth.exception.JwtTokenMalformedException;
import com.mst.auth.exception.JwtTokenMissingException;
import com.mst.auth.service.IRoleService;
import com.mst.auth.service.IUserService;
import com.mst.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController extends BaseController {
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;
    private final IUserService userService;

    private final IRoleService roleService;

    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/user")
    public MessageDto validateToken(@RequestHeader(value = "Authorization", required = false) String authorization) throws JwtTokenMalformedException, JwtTokenMissingException{
        String token = jwtUtil.parseToken(authorization);
        jwtUtil.validateToken(token);
        String username = jwtUtil.getClaims(token).getSubject();
        List<String> roles = userService.findUserRoles(username);
        Map<String, Object> data = new HashMap<>();
        data.put("roles", roles);
        return createSuccessResponse(data);
    }

    @PostMapping("/login")
    public MessageDto login(@RequestBody @Validated @Valid LoginRequestDto loginInfo) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword())
        );
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getUsername());
        UserDto user = modelMapper.map(userDetails, UserDto.class);
        return createSuccessResponse(new AuthResponseDto(token, user));
    }

    @PostMapping("/register")
    public MessageDto register(@RequestBody @Validated @Valid RegisterRequestDto registerInfo) {
        if (userService.existsByUsername(registerInfo.getUsername())) {
            return createFailureResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY.value() + "",
                    "Username existed!",
                    "Username already been taken!");
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
        if (Objects.nonNull(user.getId())) {
            return createSuccessResponse(
                    "",
                    "Register successfully!",
                    null);
        }
        return  createFailureResponse(
                HttpStatus.BAD_REQUEST.value() + "",
                "Unknown error",
                "Register failed!");
    }
}
