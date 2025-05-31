package com.example.MTS1.repository;

import com.example.MTS1.model.User;
import com.example.MTS1.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default String callExternalUrlWithRestTemplate(RestTemplate restTemplate) {
        String url = "https://jsonplaceholder.typicode.com/todos/1";
        return restTemplate.getForObject(url, String.class);
    }
}
