package com.ted.usernote.user.note.application.repository;

import com.ted.usernote.user.note.application.model.UserNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserNoteRepository extends JpaRepository<UserNote, Integer> {

    @Query("SELECT e FROM UserNote e WHERE e.favorite = true")
    List<UserNote> findFavorite();

    List<UserNote> findTop3ByOrderByCreatedAtDesc();

    List<UserNote> findByDateField(LocalDate date);


}
