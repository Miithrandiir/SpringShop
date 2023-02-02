package fr.ulco.springshop.model.dto;

import fr.ulco.springshop.model.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO {
    private int id;
    private String image;
    private ProductEntity product;
}
