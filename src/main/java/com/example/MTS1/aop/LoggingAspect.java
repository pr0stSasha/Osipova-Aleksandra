package com.example.MTS1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.MTS1..*Controller.*(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        System.out.println("➡ Метод контроллера: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* com.example.MTS1..*Controller.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();
        Object result = joinPoint.proceed();
        Instant end = Instant.now();
        System.out.println("⏱ Время выполнения " + joinPoint.getSignature().getName() + ": " + (end.toEpochMilli() - start.toEpochMilli()) + " мс");
        return result;
    }
}
