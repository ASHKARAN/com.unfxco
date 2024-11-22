package com.unfxco.usermanagement.controller;

import com.unfxco.usermanagement.dto.request.login.GroupRequestDto;
import com.unfxco.usermanagement.dto.request.login.GroupSetRequestDto;
import com.unfxco.usermanagement.dto.request.login.LoginRequestDto;
import com.unfxco.usermanagement.dto.response.login.ErrorResponse;
import com.unfxco.usermanagement.dto.response.login.LoginResponseDto;
import com.unfxco.usermanagement.model.GroupEntity;
import com.unfxco.usermanagement.model.UserEntity;
import com.unfxco.usermanagement.service.GroupService;
import com.unfxco.usermanagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/auth")
public class AuthController {




    @Autowired
    private UserService userService;


    @Autowired
    private GroupService groupService;


    @Autowired
    private MessageSource messageSource;



    @PostMapping("user/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginDto, HttpServletRequest request, Locale locale) {


        UserEntity user = userService.findByUsername(loginDto.getUsername());

        if(user == null) {
            return  ResponseEntity.notFound().build();
        }





        if(user.getPassword().equals(loginDto.getPassword())) {
            return ResponseEntity.ok(user);

        }

        return  ResponseEntity.badRequest().build();
    }




    @PostMapping("user/signup")
    public ResponseEntity<?> signup(@RequestBody LoginRequestDto loginDto, HttpServletRequest request, Locale locale) {



        UserEntity user = userService.findByUsername(loginDto.getUsername());

        if(user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse(messageSource.getMessage("user.already.exists", null,  locale)).print()
            );
        }



        UserEntity newUser = new UserEntity();
        newUser.setUsername(loginDto.getUsername());
        newUser.setPassword(loginDto.getPassword());
        newUser = userService.save(newUser);





        return  ResponseEntity.ok(newUser);
    }

    @PostMapping("user/activate-account")
    public ResponseEntity<LoginResponseDto> activateAccount(@RequestBody LoginRequestDto loginDto, HttpServletRequest request, Locale locale) {

        if(loginDto.getUsername().equals("admin") && loginDto.getPassword().equals("admin")){
            LoginResponseDto res =   new LoginResponseDto();
            res.setToken("admin-token");
            res.setUsername("admin");
            return ResponseEntity.ok(res);
        }

        return  ResponseEntity.badRequest().build();
    }
    @PostMapping("group")
    public ResponseEntity<GroupEntity> createGroup(@RequestBody GroupRequestDto dto, HttpServletRequest request, Locale locale) {


        GroupEntity group = new GroupEntity();
        group.setLabel(dto.getLabel());
        group.setRoleId(dto.getRoleId());
        group.setParentGroupId(dto.getParentGroupId());
        group.setWorkspaceId(dto.getWorkspaceId());
        group = groupService.save(group);




        return  ResponseEntity.ok(group);
    }
    @PostMapping("group/set")
    public ResponseEntity<UserEntity> setGroup(@RequestBody GroupSetRequestDto dto, HttpServletRequest request, Locale locale) {


        UserEntity user = userService.findById(dto.getUserId());

        if(user == null) {
            return  ResponseEntity.notFound().build();
        }

        GroupEntity group = groupService.findById(dto.getGroupId());

        if(group == null) {
            return  ResponseEntity.notFound().build();
        }


        List<GroupEntity> groups = user.getGroups();

        if(groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(group);
        user.setGroups(groups);
        user = userService.save(user);





        return  ResponseEntity.ok(user);
    }
}
