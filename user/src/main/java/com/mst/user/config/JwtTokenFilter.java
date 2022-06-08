package com.mst.user.config;

import com.mst.user.domain.model.UserModel;
import com.mst.user.exception.JwtTokenMalformedException;
import com.mst.user.exception.JwtTokenMissingException;
import com.mst.user.service.IUserService;
import com.mst.user.util.HttpServletResponseUtil;
import com.mst.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    private final HttpServletResponseUtil responseUtil;

    private final IUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.parseToken(request);
        if (Objects.nonNull(token)) {
            try {
                jwtUtil.validateToken(token);
            } catch (JwtTokenMalformedException | JwtTokenMissingException e) {
                int  statusCode = HttpServletResponse.SC_UNAUTHORIZED;
                if (e instanceof JwtTokenMissingException) {
                    statusCode = HttpServletResponse.SC_BAD_REQUEST;
                }
                responseUtil.createFailureResponse(request, response, statusCode, e);
                return;
            }
            String username = jwtUtil.getClaims(token).getSubject();
            UserModel user = userService.findByUsername(username);
            List<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    null,
                    null,
                    authorities
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

}
