package com.masofino.magecontroll.controllers;

import com.masofino.magecontroll.models.assignment.StudentAssign;
import com.masofino.magecontroll.services.StudentAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/assign")
public class StudentAssignController {

    @Autowired
    private StudentAssignService studentAssignService;

    @GetMapping
    public Page<StudentAssign> getAllStudentAssign(Pageable pageable) {
        return studentAssignService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentAssign> getStudentAssignById(@PathVariable int id) {
        StudentAssign studentAssign = studentAssignService.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "StudentAssign not found");
                });

        return ResponseEntity.ok(studentAssign);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudentAssign(@Validated @RequestBody StudentAssign studentAssign) {
        try {
            StudentAssign createdStudentAssign = studentAssignService.save(studentAssign);
            return new ResponseEntity<>(createdStudentAssign, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Could not create StudentAssign.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentAssign> updateStudentAssign(@PathVariable int id, @RequestBody StudentAssign studentAssign) {
        return studentAssignService.findById(id)
                .map(sa -> {

                    sa.setStudentId(studentAssign.getStudentId());
                    sa.setDatabaseId(studentAssign.getDatabaseId());
                    sa.setWebsiteId(studentAssign.getWebsiteId());
                    sa.setSubjectId(studentAssign.getSubjectId());

                    return ResponseEntity.ok(studentAssignService.save(sa));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentAssign(@PathVariable int id) {
        return studentAssignService.findById(id)
                .map(user -> {
                    studentAssignService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
