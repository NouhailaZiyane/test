package com.example.test.repository;

import com.example.test.model.Entrepot;
import com.example.test.model.Famille;
import org.springframework.data.jpa.repository.JpaRepository;

public interface entreRepo extends JpaRepository<Entrepot, Long> {

}
