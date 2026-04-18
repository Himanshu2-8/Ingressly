package com.himanshu.proxyServer.domain.dto;

import org.springframework.stereotype.Component;

public record SigninRequest(String email, String password) {
}
