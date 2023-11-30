package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.log.Log;
import com.masofino.magecontroll.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public Page<Log> findAll(Pageable pageable) {
        return logRepository.findAll(pageable);
    }

    public Optional<Log> findById(int id) {
        return logRepository.findById(id);
    }

    public Log save(Log log) {
        return logRepository.save(log);
    }

    public void deleteById(int id) {
        logRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return logRepository.existsById(id);
    }
}
