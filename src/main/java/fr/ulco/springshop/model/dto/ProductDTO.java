package fr.ulco.springshop.model.dto;

import fr.ulco.springshop.model.bo.ProductBO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static fr.ulco.springshop.controllers.ProductController.getRouteProductThumbnail;

@AllArgsConstructor
@Getter
public class ProductDTO {
    private int id;
    private String name;
    private float price;

    private int quantity;

    private String description;

    private String thumbnail;

    public ProductDTO(ProductBO productBO) {
        this.id = productBO.getId();
        this.name = productBO.getName();
        this.price = productBO.getPrice();
        this.quantity = productBO.getQuantity();
        this.description = productBO.getDescription();
        this.thumbnail = getRouteProductThumbnail(productBO);
    }

}
