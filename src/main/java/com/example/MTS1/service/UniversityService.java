package com.example.MTS1.service;

import com.example.MTS1.model.University;
import com.example.MTS1.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UniversityService {

    private final UniversityRepository universityRepository;

    public List<University> getAllUniversities() {
        log.info("Fetching all universities");
        return universityRepository.findAll();
    }

    public Optional<University> getUniversityById(Long id) {
        log.info("Fetching university by id: {}", id);
        return universityRepository.findById(id);
    }
}
