package com.example.MTS1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-books")
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference("course-users")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "university_id")
    @JsonBackReference("university-users")
    private University university;
}
