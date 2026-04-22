package com.himanshu.proxyServer.repositories;

import com.himanshu.proxyServer.domain.entities.ProxyEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProxyEndpointRepository extends JpaRepository<ProxyEndpoint, UUID> {

    List<ProxyEndpoint> findByUserId(UUID userId);
    Optional<ProxyEndpoint> findByDomainName(String domainName);
    Optional<ProxyEndpoint> findByApiKey(String apiKey);
}

