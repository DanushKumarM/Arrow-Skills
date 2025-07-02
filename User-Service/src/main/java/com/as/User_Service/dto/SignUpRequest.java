package com.as.User_Service.dto;

import com.as.User_Service.enums.Gender;
import com.as.User_Service.enums.Role;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Data
public class SignUpRequest {

    private String name;
    private String email;
    private String password;
    private String phone;
    private LocalDate dateOfBirth;
    private Gender gender;
    private Role role;

}
