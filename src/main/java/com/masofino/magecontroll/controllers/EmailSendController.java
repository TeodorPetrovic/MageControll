package com.masofino.magecontroll.controllers;

import com.masofino.magecontroll.models.email.EmailSend;
import com.masofino.magecontroll.services.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/emailsend")
public class EmailSendController {

    @Autowired
    private EmailSendService emailSendService;

    @GetMapping
    public Page<EmailSend> getAllEmailSends(Pageable pageable) {
        return emailSendService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailSend> getEmailSendById(@PathVariable int id) {
        EmailSend emailSend = emailSendService.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "EmailSend not found");
                });

        return ResponseEntity.ok(emailSend);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmailSend(@Validated @RequestBody EmailSend emailSend) {
        try {
            EmailSend createdEmailSend = emailSendService.save(emailSend);
            return new ResponseEntity<>(createdEmailSend, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Could not create EmailSend.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailSend> updateEmailSend(@PathVariable int id, @RequestBody EmailSend emailSend) {
        return emailSendService.findById(id)
                .map(ems -> {

                    ems.setIsSent(emailSend.getIsSent());
                    ems.setStudentAssignID(emailSend.getStudentAssignID());

                    return ResponseEntity.ok(emailSendService.save(ems));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmailSend(@PathVariable int id) {
        return emailSendService.findById(id)
                .map(user -> {
                    emailSendService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
