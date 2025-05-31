package com.example.MTS1.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RateLimiter(name = "defaultRateLimiter") // имя конфига
public class RateLimiterController {

    @GetMapping("/rate-limited")
    public String rateLimitedEndpoint() {
        return "Этот эндпоинт ограничен по скорости";
    }
}
