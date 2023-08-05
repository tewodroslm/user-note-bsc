package com.ted.usernote.user.note.application.services;

import com.ted.usernote.user.note.application.model.Users;
import com.ted.usernote.user.note.application.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        log.info("Custom User Details Service .." + name);
        Optional<Users> optionalUsers = userRepository.findByName(name);
        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("User name not found"));
        return null;
    }


}
