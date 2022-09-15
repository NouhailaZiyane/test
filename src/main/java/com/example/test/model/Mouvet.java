package com.example.test.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="mouvet")
public class Mouvet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(
            cascade = CascadeType.MERGE)
    @JoinColumn(name="article_id")
    Article a;

    @Column(name = "stock")
    private long stock;



    @ManyToOne(
            cascade = CascadeType.MERGE)
    @JoinColumn(name="entrepot_id")
    Entrepot e;

    public Mouvet() {
    }
    public Mouvet(long id, Article a, Entrepot e, long stock) {
        this.id = id;
        this.a = a;
        this.e = e;
        this.stock=stock;
    }
}
