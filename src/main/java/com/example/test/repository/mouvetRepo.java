package com.example.test.repository;

import com.example.test.model.Article;
import com.example.test.model.Mouvet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface mouvetRepo extends JpaRepository<Mouvet,Long> {

    @Query(value = "SELECT sum(stock) from mouvet m where m.article_id = ?1",
            nativeQuery = true)
    Long select(long id);
}

