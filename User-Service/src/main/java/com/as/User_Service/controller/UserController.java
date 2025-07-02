package com.as.User_Service.controller;

import com.as.User_Service.dto.*;
import com.as.User_Service.model.User;
import com.as.User_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        System.out.println("Login endpoint hit");
        UserResponse user = userService.register(signUpRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LogInRequest loginRequest) {
        LoginResponse response = userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.getUserById(id, token));
    }



    @GetMapping("/instructor/{id}")
    public ResponseEntity<InstructorResponseDTO> getInstructorById(@PathVariable Long id){
       InstructorResponseDTO dto = userService.getInstructorDetails(id);
       return ResponseEntity.ok(dto);
    }

}
