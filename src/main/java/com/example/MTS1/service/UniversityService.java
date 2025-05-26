package com.example.MTS1.service;

import com.example.MTS1.model.University;
import com.example.MTS1.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {
    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public List<University> fetchAllUniversities() {
        return universityRepository.findAll();
    }

    public University addUniversity(University university) {
        return universityRepository.save(university);
    }

    public Optional<University> getUniversityById(Long id) {
        return universityRepository.findById(id);
    }

}
