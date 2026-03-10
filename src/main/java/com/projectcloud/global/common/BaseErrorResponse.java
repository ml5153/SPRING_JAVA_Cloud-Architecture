package com.projectcloud.global.common;

public record BaseErrorResponse(
        int status,
        String code,
        String errorMessage
) {}
