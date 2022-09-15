package com.example.test.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="image")
public class Image {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Column(length = 50000000)
    private byte[] picByte;

    public Image(String originalFilename, String contentType, byte[] bytes) {
        this.name=originalFilename;
        this.type=contentType;
        this.picByte=bytes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Image(Long id) {
        this.id = id;
    }
    public Image(){}
}
