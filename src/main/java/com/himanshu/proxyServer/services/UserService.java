package com.himanshu.proxyServer.services;

import com.himanshu.proxyServer.domain.dto.SignupRequest;
import com.himanshu.proxyServer.domain.dto.SignupResponse;
import com.himanshu.proxyServer.domain.entities.ProxyEndpoint;
import com.himanshu.proxyServer.domain.entities.User;
import com.himanshu.proxyServer.repositories.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupResponse signup(SignupRequest request) {
        String requestUsername = request.getUsername();
        Optional<User> existingUserByEmail = userRepository.findByEmail(request.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        String password = request.getPassword();
        User user = new User(
                null,
                request.getUsername(),
                request.getEmail(),
                Objects.requireNonNull(passwordEncoder.encode(password)),
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

    public Optional<User> signin(String email, String password) throws RuntimeException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            String storedPassword = user.getPassword();
            if (passwordEncoder.matches(password, storedPassword)) {
                return Optional.of(user);
            } else {
                throw new RuntimeException("Invalid password");
            }
        }
        return Optional.empty();
    }

    public User findById(String email) {
       Optional<User> userOptional = userRepository.findByEmail(email);
       if (userOptional.isEmpty()) {
           throw new RuntimeException("User not found with email: " + email);
       }
       return userOptional.get();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
