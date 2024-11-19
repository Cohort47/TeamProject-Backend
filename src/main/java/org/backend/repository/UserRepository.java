package org.backend.repository;



import org.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Transactional
    @Modifying
    @Query("update User u set u.firstName  = ?1 where u.id = ?2")
    int updateUserFirstName(String firstName, Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.lastName  = ?1 where u.id = ?2")
    int updateUserLastName(String lastName, Long id);

    @Transactional
    @Modifying
    @Query("delete from User u where u.id = ?1")
    void deleteUserById(Long id);


   Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);


}
