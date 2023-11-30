package com.masofino.magecontroll.controllers;

import com.masofino.magecontroll.models.student.Student;
import com.masofino.magecontroll.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
                });

        return ResponseEntity.ok(student);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@Validated @RequestBody Student student) {
        try {
            Student createdStudent = studentService.save(student);
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Could not create Student.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        return studentService.findById(id)
                .map(stud -> {

                    if (student.getFirstName() != null) {
                        stud.setFirstName(student.getFirstName());
                    }
                    if (student.getLastName() != null) {
                        stud.setLastName(student.getLastName());
                    }
                    if (student.getIndeks() != null) {
                        stud.setIndeks(student.getIndeks());
                    }

                    return ResponseEntity.ok(studentService.save(stud));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        return studentService.findById(id)
                .map(user -> {
                    studentService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
