package com.masofino.magecontroll.controllers;

import com.masofino.magecontroll.models.subject.Subject;
import com.masofino.magecontroll.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public Page<Subject> getAllSubjects(Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable int id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));

        return ResponseEntity.ok(subject);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSubject(@Validated @RequestBody Subject subject) {
        try {
            Subject createdSubject = subjectRepository.save(subject);
            return new ResponseEntity<>(createdSubject, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Could not create Subject.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable int id, @RequestBody Subject subject) {
        return subjectRepository.findById(id)
                .map(sub -> {

                    if (subject.getName() != null) {
                        sub.setName(subject.getName());
                    }

                    sub.setFacultyId(subject.getFacultyId());

                    return ResponseEntity.ok(subjectRepository.save(sub));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable int id) {
        return subjectRepository.findById(id)
                .map(user -> {
                    subjectRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
