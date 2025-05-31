package com.example.MTS1.service;

import com.example.MTS1.model.User;
import com.example.MTS1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final WebClient webClient;

    private final ConcurrentMap<Long, Boolean> processedUsers = new ConcurrentHashMap<>();

    @Autowired
    public UserService(UserRepository userRepository,
                       RestTemplate restTemplate,
                       WebClient.Builder webClientBuilder) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    @Cacheable(value = "usersCache", key = "#id")
    public Optional<User> getUserById(Long id) {
        simulateDelay();
        return userRepository.findById(id);
    }

    @Cacheable(value = "usersCache", key = "'allUsers'")
    public List<User> getAllUsers() {
        simulateDelay();
        return userRepository.findAll();
    }

    @Async
    public CompletableFuture<List<User>> asyncGetAllUsers() {
        simulateDelay();
        List<User> users = userRepository.findAll();
        return CompletableFuture.completedFuture(users);
    }

    @Retryable(value = CustomException.class, maxAttempts = 5, backoff = @Backoff(delay = 10000))
    public User updateUser(Long id, User user) {
        if (Math.random() < 0.7) {
            throw new CustomException("Временная ошибка при обновлении пользователя " + id);
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public String processUserExactlyOnce(Long id) {
        if (processedUsers.putIfAbsent(id, true) != null) {
            return "Пользователь " + id + " уже обработан";
        }
        // логика обработки пользователя
        return "Пользователь " + id + " обработан точно один раз";
    }

    public String callExternalRestTemplate() {
        String url = "https://jsonplaceholder.typicode.com/todos/1";
        return restTemplate.getForObject(url, String.class);
    }

    public String callExternalWebClient() {
        Mono<String> response = webClient.get()
                .uri("/todos/2")
                .retrieve()
                .bodyToMono(String.class);
        return response.block();
    }

    private void simulateDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public static class CustomException extends RuntimeException {
        public CustomException(String msg) {
            super(msg);
        }
    }
}
