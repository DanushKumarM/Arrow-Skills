package com.as.User_Service.model;

import com.as.User_Service.enums.Gender;
import com.as.User_Service.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name")
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(nullable = false, length = 10)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean enabled = true;

}
