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

    Optional<Users> findByName(String username);
    Optional<Users> findByEmail(String email);

    //    Optional<Users> findByUsernameOrEmail(String username, String email);
    Boolean existsByEmail(String email);

//    Users findByName(String name);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM user u WHERE u.dtype=:rl"
    )
    List<Users> getMUsers(@Param("rl") String u);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "DELETE from user u where u.dtype='Manager' and u.user_id=:id"
    )
    void deleteAgent(@Param("id")int id);


    // DELETE FROM user u where u.dtype='Manager' and u.user_id=23

    @Transactional
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM user u WHERE u.dtype='Manager' and u.approve_limit<=" +
                    "( SELECT MIN(approve_limit) FROM user WHERE approve_limit >= :apl)"
    )
    Optional<List<Users>> findManagerBasedOnApprovalLimit(@Param("apl") int approveLimit);

}

// 200  a

// 500  b

// 900  c

// If ---> 400 should a , b


// Todo
// while creating payment assign list of manager   3 pt                          Done
// retrieve my payment   2pt
// As a manager retrieve all payment   2 pt
// As a manager only approve those payment that are only in my bucket  2pt



// SQL sub queries
//  Tables -> Employee             -- employid, empltitle, salary
//         -> EmployeDemograhics.  -- employid, age
//
// get list of employee based on age but with out using 'join'
// SELECT * from Employee where employeid in (
//			select employid from EmployeDemographic where age > 20 )