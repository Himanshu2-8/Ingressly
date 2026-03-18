package com.himanshu.template.domain.dto;

public record ErrorResponse(int status, String message, String details) {
}
