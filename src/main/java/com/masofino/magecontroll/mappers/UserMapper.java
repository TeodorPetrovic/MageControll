package com.masofino.magecontroll.mappers;

import com.masofino.magecontroll.dtos.UserDTO;
import com.masofino.magecontroll.entities.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }

    public static User toEntity(UserDTO dto, User originalUser) {
        if (originalUser == null) { // for creating a new user
            originalUser = new User();
            // Handle setting of passwordHash here if it's part of user creation
        }
        // Update only the fields present in DTO
        originalUser.setUsername(dto.getUsername());
        // Do not update passwordHash, createdAt, and updatedAt here
        return originalUser;
    }
}
