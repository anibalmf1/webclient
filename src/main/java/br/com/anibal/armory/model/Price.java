package br.com.anibal.armory.model;

import lombok.Data;

@Data
public class Price {
    private Integer id;
    private Integer productId;
    private Double price;
}
