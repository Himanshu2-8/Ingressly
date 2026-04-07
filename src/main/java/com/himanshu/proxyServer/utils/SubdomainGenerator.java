package com.himanshu.proxyServer.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubdomainGenerator {
    public String generateSubdomain(){
        return "svc"+ UUID.randomUUID().toString().substring(0,6); // Generate a random UUID and take the first 6 characters for the subdomain
    }
}
