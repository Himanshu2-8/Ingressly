package com.himanshu.proxyServer.repositories;

import com.himanshu.proxyServer.domain.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {

    List<Service> findByUserId(UUID userId);
    Optional<Service> findByDomainName(String domainName);
}
