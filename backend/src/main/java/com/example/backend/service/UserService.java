package com.example.backend.service;

import com.example.backend.model.entity.User;

/**
 * @author gtn
 */
public interface UserService {
    User findOne(String username);
}
