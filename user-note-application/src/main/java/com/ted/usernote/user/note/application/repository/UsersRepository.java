package com.ted.usernote.user.note.application.repository;

import com.ted.usernote.user.note.application.model.Users;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findUserByUsername(String username);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM user u WHERE u.username=:rl"
    )
    Optional<Users> findByuser(@Param("rl")String username);
}