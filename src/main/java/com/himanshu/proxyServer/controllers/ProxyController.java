package com.himanshu.proxyServer.controllers;

import com.himanshu.proxyServer.domain.entities.User;
import com.himanshu.proxyServer.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.himanshu.proxyServer.domain.dto.CreateProxyRequest;
import com.himanshu.proxyServer.domain.dto.ProxyResponse;
import com.himanshu.proxyServer.domain.entities.ProxyEndpoint;
import com.himanshu.proxyServer.services.ProxyService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProxyController {

  private final UserRepository userRepository;
  private final ProxyService proxyService;

  @PostMapping("/create-proxy")
  public ResponseEntity<ProxyResponse> createProxy(@RequestBody CreateProxyRequest request, HttpServletRequest httpServletRequest){

    try{
      String email = request.getEmail();
      if(email == null || email.isBlank()) {
        return ResponseEntity.badRequest().body(new ProxyResponse("Email must not be null or empty", null, null));
      }
      Optional<User> optionalUser = userRepository.findByEmail(email);
      if (optionalUser.isEmpty()) {
        return ResponseEntity.badRequest().body(new ProxyResponse("User not found with email: " + email, null, null));
      }

      String domainName = request.getDomainName();

      System.out.println("Received request to create proxy for email: " + email + " with domain name: " + domainName);

      ProxyEndpoint response = proxyService.createService(email, domainName);
      String finalUrl = "https://" + response.getPrefix() + ".proxyserver.com";
      System.out.println("Proxy created successfully with subdomain: " + response.getPrefix() + " and API key: " + response.getApiKey());

      return ResponseEntity.ok(new ProxyResponse("Proxy created successfully", finalUrl, response.getApiKey()));

    } catch (Exception e) {

      return ResponseEntity.badRequest().body(new ProxyResponse("Failed to create proxy", null, null));
      
    }
  }
}
