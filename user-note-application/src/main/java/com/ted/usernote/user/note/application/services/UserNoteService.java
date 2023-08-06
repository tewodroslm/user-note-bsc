package com.ted.usernote.user.note.application.services;

import com.ted.usernote.user.note.application.dto.UserNotedto;
import com.ted.usernote.user.note.application.model.UserNote;
import com.ted.usernote.user.note.application.repository.UserNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserNoteService {

    @Autowired
    private UserNoteRepository userNoteRepository;


    public UserNote add(UserNotedto userNote){
        UserNote userNote1 = UserNote.builder()
                .body(userNote.getBody())
                .favorite(userNote.isFavorite())
                .title(userNote.getTitle())
                .dateField(LocalDate.now())
                .build();
       return userNoteRepository.save(userNote1);
    }

    public List<UserNote> getAll(){
        return userNoteRepository.findAll();
    }

    public List<UserNote> getAllFavorite(){
        return userNoteRepository.findFavorite();
    }

    public List<UserNote> getRecentNote(){
        return userNoteRepository.findTop3ByOrderByCreatedAtDesc();
    }

    public UserNote findById(int id){
        return userNoteRepository.findById(id).orElseThrow();
    }

    public void delete(int id){
        userNoteRepository.deleteById(id);
    }

    public UserNote updateFavoriteStatus(int entityId, String favorite) {
        UserNote entity = userNoteRepository.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with ID: " + entityId));

        entity.setFavorite( Boolean.parseBoolean(favorite));
        return userNoteRepository.save(entity);
    }

    public UserNote editNote(UserNote userNote){
        return userNoteRepository.save(userNote);
    }

    public List<UserNote> searchByDate(LocalDate date) {
        return userNoteRepository.findByDateField(date);
    }

}
