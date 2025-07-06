package com.as.payment_service.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserResponse {

        private long id;
        private String name;
        private String email;

}
