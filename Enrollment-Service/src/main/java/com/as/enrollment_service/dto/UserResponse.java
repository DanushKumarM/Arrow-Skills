package com.as.enrollment_service.dto;


import com.as.enrollment_service.enums.Role;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserResponse {

        private long id;
        private String name;
        private String email;
        private Role role;

}
