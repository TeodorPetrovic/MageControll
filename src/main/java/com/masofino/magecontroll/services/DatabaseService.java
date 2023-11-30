package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.database.Database;
import com.masofino.magecontroll.repositories.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseService {

    @Autowired
    private DatabaseRepository databaseRepository;

    public Page<Database> findAll(Pageable pageable) {
        return databaseRepository.findAll(pageable);
    }

    public Optional<Database> findById(int id) {
        return databaseRepository.findById(id);
    }

    public Database save(Database database) {
        return databaseRepository.save(database);
    }

    public void deleteById(int id) {
        databaseRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return databaseRepository.existsById(id);
    }
}
