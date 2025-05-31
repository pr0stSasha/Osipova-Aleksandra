package com.example.MTS1.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CircuitBreaker(name = "defaultCircuitBreaker", fallbackMethod = "fallback")
public class CircuitBreakerController {

    @GetMapping("/cb-endpoint")
    public String cbEndpoint() {
        if (Math.random() < 0.5) {
            throw new RuntimeException("Имитируем ошибку");
        }
        return "Circuit breaker сработал успешно";
    }

    public String fallback(Throwable t) {
        return "Фолбэк метод: " + t.getMessage();
    }
}
