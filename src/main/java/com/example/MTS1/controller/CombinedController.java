package com.example.MTS1.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RateLimiter(name = "defaultRateLimiter")
@CircuitBreaker(name = "defaultCircuitBreaker", fallbackMethod = "fallback")
public class CombinedController {

    @GetMapping("/combined")
    public String combinedEndpoint() {
        if (Math.random() < 0.3) {
            throw new RuntimeException("Ошибка в combined");
        }
        return "Успешный ответ combined";
    }

    public String fallback(Throwable t) {
        return "Combined fallback: " + t.getMessage();
    }
}
