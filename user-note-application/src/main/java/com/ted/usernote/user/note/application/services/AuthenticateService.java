package com.ted.usernote.user.note.application.services;

import com.ted.usernote.user.note.application.dto.SignInDto;
import com.ted.usernote.user.note.application.model.Users;
import com.ted.usernote.user.note.application.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthenticateService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private HttpServletResponse httpServletResponse;


    @Autowired
    private UsersRepository usersRepository;

    public boolean authenticateUser(SignInDto authDto){
        log.info("Inside authenticate Service: {}", authDto);
        Users users = usersRepository.findUserByUsername(authDto.getUsername()).orElse(null);

        BCryptPasswordEncoder b = new BCryptPasswordEncoder();


        if(b.matches(authDto.getPassword(),users.getPassword())){
             return true;
        }else {
             return false;
        }

    }

    public Boolean logout(){
        log.info("Logout User");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(
                    httpServletRequest,
                    httpServletResponse,
                    authentication
            );
            log.info("User Logged Out Successfully");
            return true;
        }
        return false;
    }

}
