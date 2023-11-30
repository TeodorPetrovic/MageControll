package com.masofino.magecontroll.models.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateUserDTO {
    public String username;
    public String password;
    public String checkPassword;
}
