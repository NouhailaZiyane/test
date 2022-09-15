package com.example.test.repository;

import com.example.test.model.CommandeF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface cmdeFRepo extends JpaRepository<CommandeF, Long> {
    @Query(value = "select  distinct  count(*) from commandeF", nativeQuery = true)
    long countC();
    @Transactional
    @Query(value = "UPDATE commande_f c set date_cmde =?2,date_rec =?3,cond_pai =?4,reference=?5, mode_pai =?6, quantite=?7, montant =?8 where id = ?1",
            nativeQuery = true)
    @Modifying(clearAutomatically = true)
    void updateCmde(long id,String dateCmde, String dateRec ,String condPai,String reference,String modePai, double quantite,double montant);
}
