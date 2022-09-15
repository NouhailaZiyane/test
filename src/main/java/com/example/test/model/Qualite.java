package com.example.test.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "qualite")
public class Qualite {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qualite")
    private String qualite;

    @Column(name = "note")
    private String note;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "article_id", nullable = false)
    private Article a;




}
