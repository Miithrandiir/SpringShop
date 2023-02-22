package fr.ulco.springshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductDTO {
    private int id;
    private String name;
    private float price;

    private int quantity;

    private String description;

    private String thumbnail;

}
