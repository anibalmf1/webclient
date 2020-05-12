package br.com.anibal.armory.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false,  nullable = false)
    private Integer id;

    private String name;

    @Transient
    private Double price;
}
