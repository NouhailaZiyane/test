package com.example.test.repository;

import com.example.test.model.Conditionnement;
import com.example.test.model.Famille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface condRepo extends JpaRepository<Conditionnement, Long> {
    @Query(value = "SELECT * FROM conditionnement c where c.article_id=:id", nativeQuery = true)
    Conditionnement findByIdArticle(long id);

    @Query(value = "select * from Conditionnement c where c.article_id=:id", nativeQuery = true)
    Iterable<Conditionnement> afficherC(long id);

}
