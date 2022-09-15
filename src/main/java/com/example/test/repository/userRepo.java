package com.example.test.repository;

import com.example.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM utilisateur u WHERE u.nom_utilisateur = ?1 ",
            nativeQuery = true)
    Optional<User> findUser(String username);

}
