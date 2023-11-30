package com.masofino.magecontroll.models.website;

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
@Entity(name = "website")
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "website_id")
    private int id;
    private String name;
    private String path;
    @Column(name = "created_at")
    private Timestamp createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}
