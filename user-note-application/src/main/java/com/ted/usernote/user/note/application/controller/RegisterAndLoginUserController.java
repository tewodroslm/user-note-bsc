package com.ted.usernote.user.note.application.controller;

import com.ted.usernote.user.note.application.dto.SignInDto;
import com.ted.usernote.user.note.application.dto.SignUpDto;
import com.ted.usernote.user.note.application.dto.UserDto;
import com.ted.usernote.user.note.application.model.UserNote;
import com.ted.usernote.user.note.application.model.Users;
import com.ted.usernote.user.note.application.repository.UsersRepository;
import com.ted.usernote.user.note.application.services.AuthenticateService;
import com.ted.usernote.user.note.application.services.CreateUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegisterAndLoginUserController {

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    AuthenticateService authenticationService;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody @Validated SignUpDto signUpDto) throws Exception {
        log.info("AuthenticationController: registerUser: Entered!");
        log.info("Request Body: ", signUpDto);
        System.out.println("Body val ====> " + signUpDto);
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

    @PostMapping("/signIn")
    public ResponseEntity<Users> signIn(@RequestBody @Validated SignInDto authDto) throws Exception {
        log.info("Login method: Login");

        boolean auth = authenticationService.authenticateUser(authDto);
        if(auth){
            Users u = usersRepository.findUserByUsername(authDto.getUsername()).orElse(null);
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
        return new ResponseEntity<>(null , HttpStatus.FORBIDDEN);
    }

}
