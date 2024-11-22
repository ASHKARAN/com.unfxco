package com.unfxco.usermanagement.dto.request.login;

import com.unfxco.usermanagement.model.GroupEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupSetRequestDto {



    private String groupId;
    private String userId;


}
