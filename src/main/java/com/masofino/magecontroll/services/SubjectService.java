package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.subject.Subject;
import com.masofino.magecontroll.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Page<Subject> findAll(Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }

    public Optional<Subject> findById(int id) {
        return subjectRepository.findById(id);
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void deleteById(int id) {
        subjectRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return subjectRepository.existsById(id);
    }
}
