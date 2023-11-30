package com.masofino.magecontroll.models.assignment;

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
@Entity(name = "student_assign")
public class StudentAssign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_assign_id")
    private int id;
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "db_id")
    private int databaseId;
    @Column(name = "website_id")
    private int websiteId;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "subject_id")
    private int subjectId;
}
