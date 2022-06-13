package com.mst.user.client;

import com.mst.user.config.FeignClientConfig;
import com.mst.user.domain.message.ExtendedMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth", configuration = FeignClientConfig.class)
public interface AuthClient {
    @PostMapping("/api/auth/user")
    ExtendedMessage<?> validateToken();
}
