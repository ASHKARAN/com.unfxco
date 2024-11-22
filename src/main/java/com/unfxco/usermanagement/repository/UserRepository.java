package com.unfxco.usermanagement.repository;

import com.unfxco.usermanagement.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<UserEntity, Integer>{


    UserEntity findByUsername(String username);


    @Query("{ 'id' : ?0 }")
    UserEntity findById(String id);
}
