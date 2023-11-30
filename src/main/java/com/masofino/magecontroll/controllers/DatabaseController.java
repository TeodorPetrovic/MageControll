package com.masofino.magecontroll.controllers;

import com.masofino.magecontroll.models.database.Database;
import com.masofino.magecontroll.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/databases")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping
    public Page<Database> getAllDatabases(Pageable pageable) {
        return databaseService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Database> getDatabaseById(@PathVariable int id) {
        Database database = databaseService.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Database not found");
                });

        return ResponseEntity.ok(database);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDatabase(@Validated @RequestBody Database database) {
        try {
            Database createdDatabase = databaseService.save(database);
            return new ResponseEntity<>(createdDatabase, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Could not create Database.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Database> updateDatabase(@PathVariable int id, @RequestBody Database database) {
        return databaseService.findById(id)
                .map(dat -> {

                    if (database.getName() != null) {
                        dat.setName(database.getName());
                    }
                    if (database.getUsername() != null) {
                        dat.setUsername(database.getPassword());
                    }
                    if (database.getPassword() != null) {
                        dat.setPassword(database.getPassword());
                    }

                    return ResponseEntity.ok(databaseService.save(dat));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDatabase(@PathVariable int id) {
        return databaseService.findById(id)
                .map(user -> {
                    databaseService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
