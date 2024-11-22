package com.unfxco.usermanagement.service;

import com.unfxco.usermanagement.model.GroupEntity;
import com.unfxco.usermanagement.model.UserEntity;
import com.unfxco.usermanagement.repository.GroupRepository;
import com.unfxco.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class GroupService {


    @Autowired
    private GroupRepository repository;


    public GroupService(GroupRepository userRepository) {
        this.repository = userRepository;
    }


    public GroupEntity findByLabel(String label) {
        return repository.findByLabel(label);
    }


    public GroupEntity findById(String id) {
        return repository.findById(id);
    }


    public GroupEntity save(GroupEntity user) {
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        return repository.save(user);
    }
}
