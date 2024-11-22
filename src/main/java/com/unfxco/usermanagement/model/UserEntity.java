package com.unfxco.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Document(collection="user")
public class UserEntity {


    @Id
    private String id;
    private String username;
    @JsonIgnore
    private String password;


    @DBRef
    List<GroupEntity> groups;


    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;


}
