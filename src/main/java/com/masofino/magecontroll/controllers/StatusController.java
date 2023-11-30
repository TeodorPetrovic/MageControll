package com.masofino.magecontroll.controllers;

import com.masofino.magecontroll.models.status.Status;
import com.masofino.magecontroll.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping
    public Page<Status> getAllStatus(Pageable pageable) {
        return statusService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable int id) {
        Status status = statusService.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found");
                });

        return ResponseEntity.ok(status);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStatus(@Validated @RequestBody Status status) {
        try {
            Status createdStatus = statusService.save(status);
            return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Could not create Status.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable int id, @RequestBody Status status) {
        return statusService.findById(id)
                .map(sts -> {

                    if (status.getName() != null) {
                        sts.setName(status.getName());
                    }

                    return ResponseEntity.ok(statusService.save(sts));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable int id) {
        return statusService.findById(id)
                .map(user -> {
                    statusService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
