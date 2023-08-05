package com.ted.usernote.user.note.application.controller;

import com.ted.usernote.user.note.application.dto.SignUpDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
public class RegisterAndLoginUserController {

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody @Validated SignUpDto signUpDto) throws Exception {
        log.info("AuthenticationController: registerUser: Entered!");
        log.info("Request Body: ", signUpDto);

        SignUpDto signUpDto1 = SignUpDto.builder()
                .username(signUpDto.getUsername())
                .password(signUpDto.getPassword())
                .build();

        UserDto u = createUserService.createUser(signUpDto1);
        if(u == null){
            return new ResponseEntity<>(new Exception("Error creating user"), HttpStatus.OK);
        }
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

}
