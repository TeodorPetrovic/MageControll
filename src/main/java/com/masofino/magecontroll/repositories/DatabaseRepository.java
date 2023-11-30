package com.masofino.magecontroll.repositories;

import com.masofino.magecontroll.models.database.Database;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRepository extends JpaRepository<Database, Integer> {
}
