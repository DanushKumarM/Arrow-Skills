package com.as.User_Service.service;

import com.as.User_Service.dto.*;
import com.as.User_Service.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {



     UserResponse register (SignUpRequest signUpRequest);

     LoginResponse login(LogInRequest logInRequest);

     UserResponse getUserById(Long id,String token);

     InstructorResponseDTO getInstructorDetails(Long id);
}
