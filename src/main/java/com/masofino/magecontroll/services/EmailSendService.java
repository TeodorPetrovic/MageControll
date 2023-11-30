package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.email.EmailSend;
import com.masofino.magecontroll.repositories.EmailSendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailSendService {
    @Autowired
    private EmailSendRepository emailSendRepository;

    public Page<EmailSend> findAll(Pageable pageable) {
        return emailSendRepository.findAll(pageable);
    }

    public Optional<EmailSend> findById(int id) {
        return emailSendRepository.findById(id);
    }

    public EmailSend save(EmailSend emailSend) {
        return emailSendRepository.save(emailSend);
    }

    public void deleteById(int id) {
        emailSendRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return emailSendRepository.existsById(id);
    }
}
