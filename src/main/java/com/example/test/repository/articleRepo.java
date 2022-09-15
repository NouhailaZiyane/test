package com.example.test.repository;

import com.example.test.model.Article;
import com.example.test.model.Conditionnement;
import com.example.test.model.Famille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface articleRepo extends JpaRepository<Article, Long> {

    @Transactional
   @Modifying(clearAutomatically = true)
    @Query("update Article a set  a.reference =:reference, a.designation =:designation, a.f=:famille, a.typeArticle=:typeArticle, a.nomenclature=:nomenclature, a.uniteGestion=:ug, a.stock=:stock, a.codeBarre=:code where a.id =:id")
    void updateArticle(long id, String  reference, String  designation, Famille famille, String typeArticle, String nomenclature, String ug, long stock, String code);

    @Query(value = "select  distinct  count(*) from Article a WHERE a.famille_id=:id", nativeQuery = true)
    long countFa(long id);

    @Query(value = "select  distinct  sum(stock) from Article ", nativeQuery = true)
    long countStock();

    @Query(value = "Select * from Article a where a.code_barre like %:place% OR a.type_article like %:place% OR a.nomenclature like %:place% OR a.reference like %:place% OR a.designation like %:place% OR a.unite_gestion like %:place%", nativeQuery = true)
    List<String> search(String place);


    @Query(value = "Select SUM(stock) from Article a where a.id=:id", nativeQuery = true)
    long countArticleById(long id);






}
