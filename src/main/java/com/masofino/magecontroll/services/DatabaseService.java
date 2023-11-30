package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseService {

    @Autowired
    private DatabaseService databaseService;

    public Page<Database> findAll(Pageable pageable) {
        return databaseService.findAll(pageable);
    }

    public Optional<Database> findById(int id) {
        return databaseService.findById(id);
    }

    public Database save(Database database) {
        return databaseService.save(database);
    }

    public void deleteById(int id) {
        databaseService.deleteById(id);
    }

    public boolean existsById(int id) {
        return databaseService.existsById(id);
    }
}
