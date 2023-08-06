package com.ted.usernote.user.note.application.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserNotedto {

    private String title;

    private String body;

    private  boolean favorite;

}
