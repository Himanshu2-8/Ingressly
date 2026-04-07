package com.himanshu.proxyServer.repositories;

import com.himanshu.proxyServer.domain.entities.Service;
import com.himanshu.proxyServer.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
