package com.masofino.magecontroll.repositories;

import com.masofino.magecontroll.models.assignment.StudentAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAssignRepository extends JpaRepository<StudentAssign, Integer> {
}
