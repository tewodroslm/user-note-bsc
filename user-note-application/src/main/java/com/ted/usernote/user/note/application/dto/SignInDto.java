package com.ted.usernote.user.note.application.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Builder
@Data
public class SignInDto {

    @NotBlank(message = "The password property must not be blank")
    private String password;

    @NotBlank(message = "The username property must not be blank")
    private String username;

}
