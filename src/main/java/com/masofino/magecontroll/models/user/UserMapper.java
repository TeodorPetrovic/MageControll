package com.masofino.magecontroll.models.user;

import com.masofino.magecontroll.models.user.dto.CreateUserDTO;
import com.masofino.magecontroll.models.user.dto.ShowUserDTO;
import com.masofino.magecontroll.models.user.dto.UpdateUserDTO;

public class UserMapper {

    public static ShowUserDTO UserToDTO(User user) {
        ShowUserDTO dto = new ShowUserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }

    public static User postUserDTOtoEntity(CreateUserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        return user;
    }

    public static User updateUserDTOtoEntity(UpdateUserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        return user;
    }
}
