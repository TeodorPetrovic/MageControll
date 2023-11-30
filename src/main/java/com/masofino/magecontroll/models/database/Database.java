package com.masofino.magecontroll.models.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "db")
public class Database {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_id")
    private int id;
    private String name;
    private String username;
    private String password;
    @Column(name = "created_at")
    private Timestamp createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}
