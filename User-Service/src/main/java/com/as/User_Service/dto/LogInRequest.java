package com.as.User_Service.dto;

import lombok.Data;

@Data
public class LogInRequest {

    private String email;
    private String password;
}
