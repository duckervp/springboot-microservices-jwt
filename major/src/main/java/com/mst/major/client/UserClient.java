package com.mst.major.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user")
public interface UserClient {
    @GetMapping("/api/users/roles/q")
    List<String> findUserRoles(@RequestParam String username);
}
