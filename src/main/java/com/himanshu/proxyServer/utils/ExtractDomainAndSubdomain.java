package com.himanshu.proxyServer.utils;

import java.util.Arrays;

import lombok.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExtractDomainAndSubdomain {

    public static class DomainInfo {
        public String subdomain;
        public String domain;
        public DomainInfo(String subdomain, String domain) {
            this.subdomain = subdomain;
            this.domain = domain;
        }
    }
    public DomainInfo extract(String requestUri) {
        if (requestUri == null || requestUri.isBlank()) {
            throw new IllegalArgumentException("Invalid URI");
        }

        String trimmed = requestUri.substring(1);

        String firstSegment = trimmed.split("/")[0];

        String[] parts = firstSegment.split("\\.");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid domain format");
        }

        String subdomain = parts[0];

        String domain = String.join(".",
                Arrays.copyOfRange(parts, 1, parts.length));

        return new DomainInfo(subdomain, domain);
    }
}