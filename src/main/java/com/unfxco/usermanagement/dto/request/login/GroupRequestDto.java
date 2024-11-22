package com.unfxco.usermanagement.dto.request.login;

import com.unfxco.usermanagement.model.GroupEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
public class GroupRequestDto {



    private GroupEntity parentGroupId;


    private String workspaceId;

    private String label;

    private String roleId;


}
