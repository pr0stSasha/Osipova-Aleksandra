package com.example.MTS1.service;

import com.example.MTS1.model.University;
import com.example.MTS1.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;

    public List<University> findAllUniversities() {
        return universityRepository.findAll();
    }

    public Optional<University> findUniversityById(Long id) {
        return universityRepository.findById(id);
    }

    public University saveUniversity(University university) {
        return universityRepository.save(university);
    }

    public List<University> saveUniversitiesBatch(List<University> universities) {
        return universityRepository.saveAll(universities);
    }

    public Optional<University> updateUniversity(Long id, University updatedUniversity) {
        return universityRepository.findById(id).map(existingUniversity -> {
            existingUniversity.setName(updatedUniversity.getName());
            existingUniversity.setLocation(updatedUniversity.getLocation());
            return universityRepository.save(existingUniversity);
        });
    }

    public Optional<University> updateUniversityName(Long id, String name) {
        return universityRepository.findById(id).map(university -> {
            university.setName(name);
            return universityRepository.save(university);
        });
    }

    public Optional<University> patchUniversity(Long id, University partialData) {
        return universityRepository.findById(id).map(university -> {
            if (partialData.getName() != null) university.setName(partialData.getName());
            if (partialData.getLocation() != null) university.setLocation(partialData.getLocation());
            return universityRepository.save(university);
        });
    }

    public Optional<University> patchUniversityLocation(Long id, String location) {
        return universityRepository.findById(id).map(university -> {
            university.setLocation(location);
            return universityRepository.save(university);
        });
    }

    public boolean deleteUniversity(Long id) {
        if (universityRepository.existsById(id)) {
            universityRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteUniversitiesByIds(List<Long> ids) {
        universityRepository.deleteAllById(ids);
    }
    public Optional<University> patchUniversityName(Long id, String name) {
        return universityRepository.findById(id).map(university -> {
            university.setName(name);
            return universityRepository.save(university);
        });
    }
}
