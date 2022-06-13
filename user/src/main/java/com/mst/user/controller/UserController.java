package com.mst.user.controller;

import com.mst.user.domain.entity.User;
import com.mst.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController extends BaseController {
    private final IUserService userService;

    @GetMapping ()
    public ResponseEntity<?> findAllUsers() {
        return createSuccessResponse(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return createSuccessResponse(userService.findById(id));
    }

    @GetMapping("/q")
    public ResponseEntity<?> findByUsername(@RequestParam String username) {
        return createSuccessResponse(userService.findByUsername(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        User user = new User();
        user.setId(id);
        userService.delete(user);
        return createSuccessResponse();
    }
}
