package com.himanshu.proxyServer.services;

import com.himanshu.proxyServer.domain.dto.SignupRequest;
import com.himanshu.proxyServer.domain.dto.SignupResponse;
import com.himanshu.proxyServer.domain.entities.ProxyEndpoint;
import com.himanshu.proxyServer.domain.entities.User;
import com.himanshu.proxyServer.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignupResponse signup(SignupRequest request) {
        String requestUsername = request.getUsername();
        Optional<User> existingUserByEmail = userRepository.findByEmail(request.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User(
                null,
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                new ArrayList<ProxyEndpoint>(),
                null,
                null
        );
        try {
            userRepository.save(user);
            return new SignupResponse(
                    "User registered successfully",
                    user.getId()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error during user registration: " + e.getMessage());
        }
    }

    public User findById(String email) {
       Optional<User> userOptional = userRepository.findByEmail(email);
       if (userOptional.isEmpty()) {
           throw new RuntimeException("User not found with email: " + email);
       }
       return userOptional.get();
    }

}
