package com.mst.user.client;

import com.mst.user.domain.dto.ExtendedMessageDto;
import com.mst.user.domain.dto.MessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth", configuration = FeignClientConfig.class)
public interface AuthClient {
    @PostMapping("/api/auth/user")
    ExtendedMessageDto validateToken();
}
