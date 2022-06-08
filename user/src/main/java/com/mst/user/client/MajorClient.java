package com.mst.user.client;

import com.mst.user.domain.model.MajorModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "major")
public interface MajorClient {
    @GetMapping("/api/majors/{id}")
    MajorModel findById(@PathVariable Integer id);
}
