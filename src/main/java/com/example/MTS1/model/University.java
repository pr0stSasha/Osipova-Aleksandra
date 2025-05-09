package com.example.MTS1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "university")
    @JsonManagedReference(value = "university-users")
    private List<User> users;

    // Добавим конструктор с одним параметром (имя университета)
    public University(String name) {
        this.name = name;
    }
}
