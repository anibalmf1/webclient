package br.com.anibal.armory.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false,  nullable = false)
    private Integer id;

    @NotNull
    private String name;

    @Transient
    private Double price;
}
