package com.masofino.magecontroll.services;

import com.masofino.magecontroll.entities.User;
import com.masofino.magecontroll.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public User createOrUpdateUser(User user, String plainPassword) {
        if (plainPassword != null && !plainPassword.trim().isEmpty()) {
            user.setPasswordHash(passwordEncoder.encode(plainPassword));
        }
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }
}
