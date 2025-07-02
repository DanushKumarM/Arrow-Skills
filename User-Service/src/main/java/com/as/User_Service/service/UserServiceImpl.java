package com.as.User_Service.service;

import com.as.User_Service.dto.*;
import com.as.User_Service.exceptions.AccessDeniedException;
import com.as.User_Service.exceptions.EmailAlreadyExists;
import com.as.User_Service.mapper.UserMapper;
import com.as.User_Service.model.User;
import com.as.User_Service.repository.UserRepository;
import com.as.User_Service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

@Autowired
private  JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserResponse register(SignUpRequest signUpRequest) {
        if(userRepository.findByEmail(signUpRequest.getEmail()).isPresent()){
            throw new  EmailAlreadyExists("Email Already Exists");
        }
        User user = userMapper.toEntity(signUpRequest);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

      User savedUser =   userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public LoginResponse login(LogInRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->
                new RuntimeException("Invalid Email or Password"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Email or Password");
        }

        String token = jwtUtil.generateToken(user);
        UserResponse userResponse = userMapper.toResponse(user);

        return LoginResponse.builder()
                .token(token)
                .user(userResponse)
                .build();
    }

    @Override
    public UserResponse getUserById(Long id,String token) {

        String jwt = token.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(jwt);

        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));

        if (!id.equals(loggedInUser.getId())) {
            throw new AccessDeniedException(" You can only view your own profile");
        }

        return  userMapper.toResponse(loggedInUser);
    }

    @Override
    public InstructorResponseDTO getInstructorDetails(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->
                new RuntimeException("Instructor not found"));
        return new InstructorResponseDTO(user.getId(), user.getName());
    }
}
