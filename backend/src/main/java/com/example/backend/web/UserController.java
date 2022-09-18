package com.example.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.backend.model.entity.User;
import com.example.backend.service.UserService;

/**
 * @author gtn
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractApplicationController {

    @Autowired
    UserService userService;

    @GetMapping("/{username}")
    public User findOne(@PathVariable String username) {
        return userService.findOne(username);
    }
}
