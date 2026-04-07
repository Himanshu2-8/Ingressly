package com.himanshu.proxyServer.services;

import com.himanshu.proxyServer.domain.entities.ProxyEndpoint;
import com.himanshu.proxyServer.domain.entities.User;
import com.himanshu.proxyServer.repositories.UserRepository;
import com.himanshu.proxyServer.utils.ApiKeyGenerator;
import com.himanshu.proxyServer.utils.SubdomainGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final UserRepository userRepository;
    private final SubdomainGenerator subdomainGenerator;
    private final ApiKeyGenerator apiKeyGenerator;

    public ProxyEndpoint createService(UUID userID, String domainName) {
        Optional<User> existingUser = userRepository.findById(userID);
        if (existingUser.isEmpty()) {
            throw new NoSuchElementException("User not found with ID: " + userID);
        }
        User user = existingUser.get();
        String subdomain = subdomainGenerator.generateSubdomain();
        String apiKey=apiKeyGenerator.generateApiKey();
        return new ProxyEndpoint(
                null,
                subdomain,
                domainName,
                apiKey,
                user,
                true,
                null,
                null
        );
    }

}
