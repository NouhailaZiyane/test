package com.example.test.model;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "Famille")
public class Famille {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "intitule")
    private String intitule;

}
