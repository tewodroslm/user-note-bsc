package com.ted.usernote.user.note.application.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserNote {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private int noteId;

    private String title;

    private String body;

    private  boolean favorite;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "date_field")
    private LocalDate dateField;
    // Other fields and methods

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
