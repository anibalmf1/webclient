package br.com.anibal.armory.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Data
public class Product {

    @Id
    private Integer id;

    private String name;

    @Transient
    private Double price;
}
