package com.example.test.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "utilisateur")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "nomUtilisateur")
    private String nomUtilisateur;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;



}
