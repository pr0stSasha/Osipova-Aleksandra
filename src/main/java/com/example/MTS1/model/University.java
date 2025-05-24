package com.example.MTS1.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "universities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses;

}
