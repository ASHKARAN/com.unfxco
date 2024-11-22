package com.unfxco.usermanagement.service;

import com.unfxco.usermanagement.model.UserEntity;
import com.unfxco.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity findById(String id) {
        return userRepository.findById(id);
    }


    public UserEntity save(UserEntity user) {
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        return userRepository.save(user);
    }
}
