package com.unfxco.usermanagement.repository;

import com.unfxco.usermanagement.model.GroupEntity;
import com.unfxco.usermanagement.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface GroupRepository extends MongoRepository<GroupEntity, Integer>{


    GroupEntity findByLabel(String username);

    @Query("{ 'id' : ?0 }")
    GroupEntity findById(String id);


}
