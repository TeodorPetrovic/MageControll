package com.masofino.magecontroll.repositories;

import com.masofino.magecontroll.models.student.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Subject, Integer> {
}
