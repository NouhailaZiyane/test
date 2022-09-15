package com.example.test.repository;

import com.example.test.model.Famille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface familleRepo extends JpaRepository<Famille, Long> {

    @Transactional
    @Query(value = "UPDATE famille f set code =?3, intitule=?2 where id = ?1",
            nativeQuery = true)
    @Modifying(clearAutomatically = true)
    void updateFamille(long id,String intitule,String code);
}
