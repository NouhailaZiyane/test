package com.example.test.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@DynamicUpdate
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "reference")
    private String reference;
    @Column(name = "designation")
    private String designation;
    @Column(name = "type_article")
    private String typeArticle;
    @Column(name = "code_barre")
    private String codeBarre;
    @Column(name = "nomenclature")
    private String nomenclature;
    @Column(name = "unite_gestion")
    private String uniteGestion;

    @Column(name = "stock")
    private long stock;



    private String imagesAr;


    @ManyToOne
    @JoinColumn(name = "famille_id")
    private Famille f;

    public Article() {

    }

    public Article(String reference, String designation, String typeArticle, String codeBarre, String nomenclature, String uniteGestion, long stock, String imagesAr, Famille f) {
        this.reference = reference;
        this.designation = designation;
        this.typeArticle = typeArticle;
        this.codeBarre = codeBarre;
        this.nomenclature = nomenclature;
        this.uniteGestion = uniteGestion;
        this.stock = stock;
        this.imagesAr = imagesAr;
        this.f = f;
    }

//<img th:src="/@{${article.photosImagePath}}" />

}
