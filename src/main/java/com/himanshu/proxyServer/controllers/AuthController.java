package com.himanshu.proxyServer.controllers;

import com.himanshu.proxyServer.domain.dto.SigninRequest;
import com.himanshu.proxyServer.domain.dto.SigninResponse;
import com.himanshu.proxyServer.domain.dto.SignupRequest;
import com.himanshu.proxyServer.domain.dto.SignupResponse;
import com.himanshu.proxyServer.domain.entities.User;
import com.himanshu.proxyServer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        try {
            SignupResponse response = userService.signup(signupRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new SignupResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<SigninResponse> login(@RequestBody SigninRequest signinRequest){
        String email = signinRequest.email();
        String password = signinRequest.password();
        try {
            Optional<User> storedUer = userService.signin(email, password);
            if (storedUer.isPresent()) {
                User user = storedUer.get();
                return ResponseEntity.ok(new SigninResponse("Login successful", null, 200));
            } else {
                return ResponseEntity.badRequest().body(new SigninResponse("User not found", null, 404));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new SigninResponse(e.getMessage(), null, 400));
        }
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email){
        try {
            User user = userService.findById(email);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> findAllUsers(){
        try{
            userService.findAllUsers();
            return ResponseEntity.ok(userService.findAllUsers());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
