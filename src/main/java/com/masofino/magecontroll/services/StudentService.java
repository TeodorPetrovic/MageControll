package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.student.Subject;
import com.masofino.magecontroll.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Page<Subject> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Optional<Subject> findById(int id) {
        return studentRepository.findById(id);
    }

    public Subject save(Subject student) {
        return studentRepository.save(student);
    }

    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return studentRepository.existsById(id);
    }

}
