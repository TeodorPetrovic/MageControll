package com.masofino.magecontroll.controllers;

import com.masofino.magecontroll.models.user.dto.CreateUserDTO;
import com.masofino.magecontroll.models.user.dto.ShowUserDTO;
import com.masofino.magecontroll.models.user.User;
import com.masofino.magecontroll.models.user.UserMapper;
import com.masofino.magecontroll.models.user.dto.UpdateUserDTO;
import com.masofino.magecontroll.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<ShowUserDTO> getAllUsers(Pageable pageable) {
        return userService.findAll(pageable).map(UserMapper::UserToDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowUserDTO> getUserById(@PathVariable int id) {
        return userService.findById(id)
                .map(UserMapper::UserToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ShowUserDTO> createUser(@RequestBody CreateUserDTO psd) {
        User user = UserMapper.postUserDTOtoEntity(psd);
        User savedUser = userService.createUser(user, psd.getPassword());
        return ResponseEntity.ok(UserMapper.UserToDTO(savedUser));
    }

    //Inactive
    @PutMapping("/{id}")
    public ResponseEntity<ShowUserDTO> updateUser(@PathVariable int id, @RequestBody UpdateUserDTO uud) {

        if (uud.getPassword() != null && !uud.getPassword().isEmpty() && uud.getPassword().equals(uud.getCheckPassword())) {
            return userService.updateUser(id, uud, true)
                    .map(UserMapper::UserToDTO)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //Inactive
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        if (userService.existsById(id)) {
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
