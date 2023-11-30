package com.masofino.magecontroll.models.email;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "email_send")
public class EmailSend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_send_id")
    private int id;
    @Column(name = "student_assign_id")
    private int studentAssignID;
    @Column(name = "is_sent")
    private int isSent;
}
