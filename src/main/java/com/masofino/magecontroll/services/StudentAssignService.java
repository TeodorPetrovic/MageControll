package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.assignment.StudentAssign;
import com.masofino.magecontroll.repositories.StudentAssignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentAssignService {

    @Autowired
    private StudentAssignRepository studentAssignRepository;

    public Page<StudentAssign> findAll(Pageable pageable) {
        return studentAssignRepository.findAll(pageable);
    }

    public Optional<StudentAssign> findById(int id) {
        return studentAssignRepository.findById(id);
    }

    public StudentAssign save(StudentAssign studentAssign) {
        return studentAssignRepository.save(studentAssign);
    }

    public void deleteById(int id) {
        studentAssignRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return studentAssignRepository.existsById(id);
    }
}
