package com.himanshu.proxyServer.utils;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HexFormat;

@Service
public class ApiKeyGenerator {
    private final SecureRandom secureRandom = new SecureRandom();

    public String generateApiKey(){
        byte[] randomBytes=new byte[32]; // array if 32 bytes = 256 bits
        secureRandom.nextBytes(randomBytes); // fill the array with random bytes
        return HexFormat.of().formatHex(randomBytes); // convert the byte array to a hexadecimal string
    }
}
