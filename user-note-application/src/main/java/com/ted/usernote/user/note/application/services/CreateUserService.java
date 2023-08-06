package com.ted.usernote.user.note.application.services;

import com.ted.usernote.user.note.application.dto.SignUpDto;
import com.ted.usernote.user.note.application.dto.UserDto;
import com.ted.usernote.user.note.application.model.Users;
import com.ted.usernote.user.note.application.repository.UsersRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@Builder
public class CreateUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // create user
    public UserDto createUser(SignUpDto signUpDto){
        log.info("Create user service: Entered");
        System.out.println("\n++++++++++find by +++++++++++++");
//        System.out.println("find by " + userRepository.findByusername(signUpDto.getUsername()));
        System.out.println("\n+++++++++++++++++++++++\n");
        Users users = userRepository.findUserByUsername(signUpDto.getUsername()).orElse(null);
        if(users != null){
            log.error("User Already Exists!");
            return null;
        }

        Users u;

        Users user = Users.builder()
                    .username(signUpDto.getUsername())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .build();
        log.debug("User val ==> ", user);
        u = userRepository.save(user);

        if(u != null){
            return UserDto.builder()
                    .uname(u.getUsername())
                    .build();
        }

        return null;
    }


}
