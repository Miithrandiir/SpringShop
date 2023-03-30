package fr.ulco.springshop.model.dto;

import fr.ulco.springshop.controllers.CategoryController;
import fr.ulco.springshop.model.bo.ProductBO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static fr.ulco.springshop.controllers.CategoryController.getRouteCategory;
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

    private List<String> categories;

    public ProductDTO(ProductBO productBO) {
        this.id = productBO.getId();
        this.name = productBO.getName();
        this.price = productBO.getPrice();
        this.quantity = productBO.getQuantity();
        this.description = productBO.getDescription();
        this.thumbnail = getRouteProductThumbnail(productBO);
        this.categories = productBO.getCategories().stream().map(CategoryController::getRouteCategory).collect(Collectors.toList());
    }

}
