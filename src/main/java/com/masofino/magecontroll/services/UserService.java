package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.user.User;
import com.masofino.magecontroll.models.user.dto.UpdateUserDTO;
import com.masofino.magecontroll.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    public Optional<User> updateUser(int id, UpdateUserDTO uud, boolean hasPassword) {
        return userRepository.findById(id)
                .map(user -> {
                    // Update fields here
                    user.setUsername(uud.getUsername());

                    if (hasPassword) {
                        user.setPasswordHash(passwordEncoder.encode(uud.getPassword()));
                    }

                    return userRepository.save(user);
                });
    }

    public User createUser(User user, String plainPassword) {
        user.setPasswordHash(passwordEncoder.encode(plainPassword));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }
}
