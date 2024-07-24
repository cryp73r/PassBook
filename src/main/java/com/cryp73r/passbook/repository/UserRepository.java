package com.cryp73r.passbook.repository;

import com.cryp73r.passbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT usr FROM User usr WHERE usr.username = :username")
    Optional<User> findByUsername(String username);
}
