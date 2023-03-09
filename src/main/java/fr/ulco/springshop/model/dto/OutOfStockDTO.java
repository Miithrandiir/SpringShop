package fr.ulco.springshop.model.dto;

import fr.ulco.springshop.model.bo.ProductBO;
import lombok.Getter;

import java.util.Collection;
import java.util.stream.Collectors;

import static fr.ulco.springshop.controllers.ProductController.getRouteProductThumbnail;

@Getter
public class OutOfStockDTO {
    private final String message;
    private final Collection<ProductDTO> products;

    public OutOfStockDTO(Collection<ProductBO> products) {
        this.message = "Out of stock";
        this.products = products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }
}
