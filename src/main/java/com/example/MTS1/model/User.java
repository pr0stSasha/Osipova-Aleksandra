package com.example.MTS1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"user\"") // Экранируем имя таблицы, чтобы избежать конфликта с зарезервированным словом
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
