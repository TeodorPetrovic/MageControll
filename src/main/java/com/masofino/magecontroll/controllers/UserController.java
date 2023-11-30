package com.masofino.magecontroll.controllers;

import com.masofino.magecontroll.dtos.UserDTO;
import com.masofino.magecontroll.entities.User;
import com.masofino.magecontroll.mappers.UserMapper;
import com.masofino.magecontroll.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userService.findAll(pageable).map(UserMapper::toDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        return userService.findById(id)
                .map(UserMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO, null);
        User savedUser = userService.createOrUpdateUser(user, userDTO.getPassword());
        return ResponseEntity.ok(UserMapper.toDTO(savedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        return userService.findById(id)
                .map(entity -> {
                    User updatedUser = UserMapper.toEntity(userDTO, entity);
                    updatedUser = userService.createOrUpdateUser(updatedUser, userDTO.getPassword());
                    return ResponseEntity.ok(UserMapper.toDTO(updatedUser));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        if (userService.existsById(id)) {
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
