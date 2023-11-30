package com.masofino.magecontroll.repositories;

import com.masofino.magecontroll.models.email.EmailSend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSendRepository extends JpaRepository<EmailSend, Integer> {
}
