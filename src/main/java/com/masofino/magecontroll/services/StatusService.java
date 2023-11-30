package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.status.Status;
import com.masofino.magecontroll.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public Page<Status> findAll(Pageable pageable) {
        return statusRepository.findAll(pageable);
    }

    public Optional<Status> findById(int id) {
        return statusRepository.findById(id);
    }

    public Status save(Status status) {
        return statusRepository.save(status);
    }

    public void deleteById(int id) {
        statusRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return statusRepository.existsById(id);
    }
}
