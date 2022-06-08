package com.mst.user.controller;

import com.mst.user.domain.entity.User;
import com.mst.user.domain.model.UserModel;
import com.mst.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final IUserService userService;

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
        return userService.findByUsername(username);
    }

    @GetMapping("/roles/q")
    public List<String> findUserRoles(@RequestParam String username) {
        return userService.findUserRoles(username);
    }

    @GetMapping("/exists")
    public Boolean checkUsernameExists(@RequestParam String username) {
        return userService.existsByUsername(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        User user = new User();
        user.setId(id);
        userService.delete(user);
    }
}
