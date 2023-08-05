package com.ted.usernote.user.note.application.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class SignUpDto {

    @NotBlank(message = "The password property must not be blank")
    private String password;

    @NotBlank(message = "The username property must not be blank")
    private String username;

}
