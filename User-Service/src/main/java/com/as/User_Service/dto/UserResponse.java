package com.as.User_Service.dto;

import com.as.User_Service.enums.Gender;
import com.as.User_Service.enums.Role;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserResponse {

        private String name;
        private String email;
        private Role role;

}
