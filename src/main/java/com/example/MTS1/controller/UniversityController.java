package com.example.MTS1.controller;

import com.example.MTS1.model.University;
import com.example.MTS1.service.UniversityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {
    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public List<University> getUniversities() {
        return universityService.fetchAllUniversities();
    }

    @PostMapping
    public University createUniversity(@RequestBody University university) {
        return universityService.addUniversity(university);
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversity(@PathVariable Long id) {
        return universityService.getUniversityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
