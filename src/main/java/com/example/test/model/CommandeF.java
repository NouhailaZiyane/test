package com.example.test.model;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@Table(name = "commandeF")
public class CommandeF {
    public CommandeF(long id, String dateCmde, String dateRec, String condPai, String reference, String modePai, double quantite, double montant, Conditionnement c, Fournisseur f, Article a) {
        this.id = id;
        this.datecmde = dateCmde;
        this.daterec = dateRec;
        this.condPai = condPai;
        this.reference = reference;
        this.modePai = modePai;
        this.quantite = quantite;
        this.montant = montant;
        this.c = c;
        this.f = f;
        this.a = a;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_cmde")
    private String datecmde;

    @Column(name = "date_rec")
    private String daterec;

    @Column(name = "cond_pai")
    private String condPai;

    @Column(name = "reference")
    private String reference;

    @Column(name = "mode_pai")
    private String modePai;

    @Column(name = "quantite")
    private double quantite;

    @Column(name = "montant")
    private double montant;
    @ManyToOne(
            cascade = CascadeType.MERGE)
    @JoinColumn(name="unite_id")
    private Conditionnement c;

    @ManyToOne(
            cascade = CascadeType.MERGE)
    @JoinColumn(name="fourni_id")
    private Fournisseur f;

    @ManyToOne(
            cascade = CascadeType.MERGE)
    @JoinColumn(name="article_id")
    private Article a;

    public CommandeF(){

    }
}
