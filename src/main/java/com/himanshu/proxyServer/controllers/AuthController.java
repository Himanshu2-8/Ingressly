package com.himanshu.proxyServer.controllers;

import com.himanshu.proxyServer.domain.dto.SignupRequest;
import com.himanshu.proxyServer.domain.dto.SignupResponse;
import com.himanshu.proxyServer.domain.entities.User;
import com.himanshu.proxyServer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email){
        try {
            User user = userService.findById(email);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
