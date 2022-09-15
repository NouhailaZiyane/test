package com.example.test.repository;

import com.example.test.model.CommandeC;
import com.example.test.model.CommandeF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface cmdeCRepo extends JpaRepository<CommandeC, Long> {
    @Query(value = "select  distinct  count(*) from commandeC", nativeQuery = true)
    long countC();
}
