package com.example.MTS1.repository;

import com.example.MTS1.model.User;
import com.example.MTS1.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByCourse(Course course);
}
