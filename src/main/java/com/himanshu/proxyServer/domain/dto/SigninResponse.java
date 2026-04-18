package com.himanshu.proxyServer.domain.dto;

public record SigninResponse(String messagem, String jwtToken, int statusCode) {
}
