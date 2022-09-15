package com.example.test.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "conditionnement")
public class Conditionnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "unite")
    private String unite;

    @Column(name = "quantite")
    private long qte;

    @Column(name = "prixV")
    private  double prixV;

    @Column(name = "prixA")
    private double prixA;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article a;
}
