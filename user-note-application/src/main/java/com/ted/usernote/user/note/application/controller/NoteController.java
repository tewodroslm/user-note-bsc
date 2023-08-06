package com.ted.usernote.user.note.application.controller;

import com.ted.usernote.user.note.application.dto.UserNotedto;
import com.ted.usernote.user.note.application.model.UserNote;
import com.ted.usernote.user.note.application.services.AuthenticateService;
import com.ted.usernote.user.note.application.services.UserNoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class NoteController {

    @Autowired
    AuthenticateService authenticationService;

    @Autowired
    private UserNoteService userNoteService;

    // add note
    @PostMapping("/addnote")
    public UserNote addUserNote(@RequestBody UserNotedto userNote){
        return userNoteService.add(userNote);
    }


    // get all note
    @GetMapping("/notes")
    public List<UserNote> allNotes(){
        return userNoteService.getAll();
    }

    @GetMapping("/favorites")
    public List<UserNote> AllFavorite(){
        return userNoteService.getAllFavorite();
    }

    @GetMapping("/recent")
    public List<UserNote> getRecent(){
        return userNoteService.getRecentNote();
    }

    // view single note by id
    @GetMapping("/note/{id}")
    public UserNote note(@PathVariable("id") int userNoteId){
        return userNoteService.findById(userNoteId);
    }

    // delete note
    @DeleteMapping("/deleteNote/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE})
    public void delete(@PathVariable("id") int id){
        userNoteService.delete(id);
    }


    //edit note add favorite or remove favorite
    @PutMapping("/note/{id}/favorite")
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT})
    public UserNote favoriteChange( @PathVariable("id") int entityId,
                                    @RequestParam("favorite") String favorite){
        UserNote updatedEntity = userNoteService.updateFavoriteStatus(entityId, favorite);
        return updatedEntity;
    }

    // edit Note
    @PutMapping("/note/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT})
    public UserNote updateEntity(@PathVariable("id") int id, @RequestBody UserNotedto updatedEntity) {

        UserNote existingEntity = userNoteService.findById(id);
        // Update the fields of the existing entity with the values from updatedEntity
        existingEntity.setBody(updatedEntity.getBody());
        existingEntity.setTitle(updatedEntity.getTitle());
        existingEntity.setFavorite(updatedEntity.isFavorite());


        UserNote savedEntity = userNoteService.editNote(existingEntity);
        return savedEntity;
    }


    @GetMapping("/note/search")
    public List<UserNote> searchByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        System.out.println("\n++++++++++++++++++++++");
        System.out.println(date);
        System.out.println("\n++++++++++++++++++++++\n");
        return userNoteService.searchByDate(date);
    }

}
