package com.masofino.magecontroll.controllers;

import com.masofino.magecontroll.models.log.Log;
import com.masofino.magecontroll.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/logs")
public class LogController {
    
    @Autowired
    private LogService logService;

    @GetMapping
    public Page<Log> getAllLog(Pageable pageable) {
        return logService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Log> getLogById(@PathVariable int id) {
        Log log = logService.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Log not found");
                });

        return ResponseEntity.ok(log);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLog(@Validated @RequestBody Log log) {
        try {
            Log createdLog = logService.save(log);
            return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Could not create Log.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Log> updateLog(@PathVariable int id, @RequestBody Log log) {
        return logService.findById(id)
                .map(l -> {

                    l.setActionId(log.getActionId());
                    l.setUserId(log.getUserId());
                    l.setStatusId(log.getStatusId());

                    return ResponseEntity.ok(logService.save(l));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLog(@PathVariable int id) {
        return logService.findById(id)
                .map(user -> {
                    logService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
