package com.mst.user.controller;

import com.mst.user.domain.model.UserModel;
import com.mst.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final IUserService userService;

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping ()
    public List<UserModel> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserModel findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/q")
    public UserModel findByUsername(@RequestParam String username) {

        return userService.findUserModelByUsername(username);
    }

    @GetMapping("/exists")
    public Boolean checkUsernameExists(@RequestParam String username) {
        return userService.existsByUsername(username);
    }
}
