package com.mst.user.client;

import com.mst.user.domain.dto.ExtendedMessageDto;
import com.mst.user.domain.model.MajorModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "major", configuration = FeignClientConfig.class)
public interface MajorClient {
    @GetMapping("/api/majors/{id}")
    ExtendedMessageDto findById(@PathVariable Integer id);
}
