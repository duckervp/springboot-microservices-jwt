package com.mst.major.config;

import com.mst.major.client.AuthClient;
import com.mst.major.domain.dto.ExtendedMessageDto;
import com.mst.major.domain.dto.MessageDto;
import com.mst.major.util.HttpServletResponseUtil;
import feign.FeignException;
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
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final AuthClient authClient;

    private final HttpServletResponseUtil responseUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MessageDto responseMessage;
        try {
            responseMessage =  authClient.validateToken();
        } catch (FeignException e) {
            MessageDto message = new MessageDto(
                    "401",
                    false,
                    "Validate token fail!",
                    "Authorization header is required!"
            );
            responseUtil.sendFailureResponse(response, message);
            return;
        }

        if (!responseMessage.getStatus()) {
            responseUtil.sendFailureResponse(response, responseMessage);
            return;
        }
        List<String> roles = (List<String>) ((ExtendedMessageDto<Map>) responseMessage).getData().get("roles");
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                null,
                null,
                authorities
        );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

}
