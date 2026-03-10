package com.projectcloud.global.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class Logger {

    @Before("execution(* com.projectcloud.domain.member.controller..*.*(..))")
    public void logApiRequest(JoinPoint joinPoint) {
        log.info("[API - LOG] {} 메서드 호출", joinPoint.getSignature().getName());
    }
}
