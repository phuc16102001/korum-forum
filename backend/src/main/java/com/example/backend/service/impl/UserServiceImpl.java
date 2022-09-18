package com.example.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.backend.model.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;

/**
 * @author gtn
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findOne(String username) {
        return userRepository.findUserByUsername(username);
    }
}
