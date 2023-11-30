package com.masofino.magecontroll.controllers;

import com.masofino.magecontroll.models.faculty.Faculty;
import com.masofino.magecontroll.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public Page<Faculty> getAllFaculty(Pageable pageable) {
        return facultyService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable int id) {
        Faculty faculty = facultyService.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Faculty not found");
                });

        return ResponseEntity.ok(faculty);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFaculty(@Validated @RequestBody Faculty faculty) {
        try {
            Faculty createdFaculty = facultyService.save(faculty);
            return new ResponseEntity<>(createdFaculty, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Could not create Faculty.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable int id, @RequestBody Faculty faculty) {
        return facultyService.findById(id)
                .map(fac -> {

                    if (faculty.getName() != null) {
                        fac.setName(faculty.getName());
                    }

                    return ResponseEntity.ok(facultyService.save(fac));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable int id) {
        return facultyService.findById(id)
                .map(user -> {
                    facultyService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
