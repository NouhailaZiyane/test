package com.example.test.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "mail")
    private String mail;

    @Column(name = "tel")
    private String tel;

    @Column(name = "cPostal")
    private String cPostal;

    @Column(name = "fax")
    private String fax;

    @Column(name = "pays")
    private String pays;

    @Column(name = "ville")
    private String ville;

    @Column(name = "adresse")
    private String adresse;

}
