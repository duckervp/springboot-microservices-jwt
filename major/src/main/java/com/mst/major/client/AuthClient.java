package com.mst.major.client;

import com.mst.major.config.FeignClientConfig;
import com.mst.major.domain.message.ExtendedMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "auth", configuration = FeignClientConfig.class)
public interface AuthClient {
    @PostMapping("/api/auth/user")
    ExtendedMessage<?> validateToken();
}
