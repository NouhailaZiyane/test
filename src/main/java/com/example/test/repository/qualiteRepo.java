package com.example.test.repository;

import com.example.test.model.Qualite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface qualiteRepo  extends JpaRepository<Qualite, Long> {
    @Query(value = "SELECT * FROM qualite WHERE article_id=:id", nativeQuery = true)
    Qualite getQualite(long id);

    @Transactional
    @Query(value = "UPDATE qualite q set q.note=:note, q.qualite=:quali WHERE id=:id", nativeQuery = true)
    @Modifying(clearAutomatically = true)
    void updateQualite(long id, String note, String quali);
}
