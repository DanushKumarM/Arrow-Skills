package com.as.User_Service.mapper;

import com.as.User_Service.dto.SignUpRequest;
import com.as.User_Service.dto.UserResponse;
import com.as.User_Service.enums.Role;
import com.as.User_Service.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(SignUpRequest signup) {

        Role role = signup.getRole();

        if (role == null || role == Role.ADMIN) {
            role = Role.STUDENT; // fallback to STUDENT
        }

        return User.builder()
                .name(signup.getName())
                .email(signup.getEmail())
                .password(signup.getPassword())
                .phone(signup.getPhone())
                .dateOfBirth(signup.getDateOfBirth())
                .role(role)
                .gender(signup.getGender())
                .enabled(true)
                .build();

    }
    public UserResponse toResponse(User user){
        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
