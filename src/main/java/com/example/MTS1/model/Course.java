package com.example.MTS1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "course")
    @JsonBackReference(value = "course-users")
    private List<User> users;

    // Добавим конструктор с двумя параметрами
    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
