package com.masofino.magecontroll.models.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ShowUserDTO {
    private int id;
    private String username;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
