package com.mst.user.client;

import com.mst.user.config.FeignClientConfig;
import com.mst.user.domain.message.ExtendedMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "major", configuration = FeignClientConfig.class)
public interface MajorClient {
    @GetMapping("/api/majors/{id}")
    ExtendedMessage<?> findById(@PathVariable Integer id);
}
