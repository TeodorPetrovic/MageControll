package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.faculty.Faculty;
import com.masofino.magecontroll.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public Page<Faculty> findAll(Pageable pageable) {
        return facultyRepository.findAll(pageable);
    }

    public Optional<Faculty> findById(int id) {
        return facultyRepository.findById(id);
    }

    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteById(int id) {
        facultyRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return facultyRepository.existsById(id);
    }
}
