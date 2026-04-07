package com.himanshu.proxyServer.utils;

import org.apache.commons.validator.routines.DomainValidator;

public class ExtractDomain {

    public String extract(String url) {
        if(url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        return null;
    }
}
