package com.example.test.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="entrepot")
public class Entrepot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "intitule")
    private String intitule;

    @Column(name = "mail")
    private String mail;

    @Column(name = "tel")
    private String tel;

    @Column(name = "cPostal")
    private String cpostal;

    @Column(name = "ville")
    private String ville;

    public Entrepot() {
    }

    @Column(name = "adresse")
    private String adresse;



    public Entrepot(long id, String intitule, String mail, String tel, String cpostal, String ville, String adresse) {
        this.id = id;
        this.intitule = intitule;
        this.mail = mail;
        this.tel = tel;
        this.cpostal = cpostal;
        this.ville = ville;
        this.adresse = adresse;
    }
}
