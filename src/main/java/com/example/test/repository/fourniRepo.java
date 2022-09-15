package com.example.test.repository;


import com.example.test.model.Client;
import com.example.test.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface fourniRepo extends JpaRepository<Fournisseur, Long> {
    @Query(value = "select  distinct  count(*) from Fournisseur", nativeQuery = true)
    long countF();
    @Transactional
    @Query(value = "UPDATE fournisseur set nom =?2,prenom =?3,mail =?4,tel=?5, c_postal =?6, fax=?7, pays =?8, ville=?9, adresse=?10 where id = ?1",
            nativeQuery = true)
    @Modifying(clearAutomatically = true)
    void updateFourni(long id,String nom, String prenom,String mail,String tel,String cPostal,String fax,String pays,String ville,String adresse);

}
