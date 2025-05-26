package com.example.MTS1.controller;

import com.example.MTS1.api.UniversityControllerDocs;
import com.example.MTS1.model.University;
import com.example.MTS1.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
@RequiredArgsConstructor
public class UniversityController implements UniversityControllerDocs {

    private final UniversityService universityService;

    @GetMapping
    public List<University> getAllUniversities() {
        return universityService.findAllUniversities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversityById(@PathVariable Long id) {
        return universityService.findUniversityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public University createUniversity(@RequestBody University university) {
        return universityService.saveUniversity(university);
    }

    @PostMapping("/batch")
    public List<University> createUniversitiesBatch(@RequestBody List<University> universities) {
        return universityService.saveUniversitiesBatch(universities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id, @RequestBody University university) {
        return universityService.updateUniversity(id, university)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/location")
    public ResponseEntity<University> updateUniversityLocation(@PathVariable Long id, @RequestBody University university) {
        return universityService.patchUniversityLocation(id, university.getLocation())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<University> patchUniversity(@PathVariable Long id, @RequestBody University university) {
        return universityService.patchUniversity(id, university)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<University> patchUniversityName(@PathVariable Long id, @RequestBody University university) {
        return universityService.patchUniversityName(id, university.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable Long id) {
        if (universityService.deleteUniversity(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> deleteUniversitiesBatch(@RequestBody List<Long> ids) {
        universityService.deleteUniversitiesByIds(ids);
        return ResponseEntity.noContent().build();
    }
}
