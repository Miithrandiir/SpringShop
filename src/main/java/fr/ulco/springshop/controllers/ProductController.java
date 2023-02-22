package fr.ulco.springshop.controllers;

import fr.ulco.springshop.model.dto.ProductDTO;
import fr.ulco.springshop.service.core.ProductServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceInterface productService;

    @GetMapping(Routes.GET_PRODUCTS)
    public ResponseEntity<Collection<ProductDTO>> getProducts() {

        return ResponseEntity.ok(productService
                .findAll()
                .stream()
                .map(x -> new ProductDTO(x.getId(), x.getName(), x.getPrice(), x.getQuantity(), x.getDescription(), x.getThumbnail()))
                .collect(Collectors.toList())
        );
    }

    @GetMapping(Routes.GET_PRODUCTS_BY_CATEGORY)
    public ResponseEntity<Collection<ProductDTO>> getProductsByCategory(@PathVariable String slug) {
        return ResponseEntity.ok(productService
                .findByCategory(slug)
                .stream()
                .map(x -> new ProductDTO(x.getId(), x.getName(), x.getPrice(), x.getQuantity(), x.getDescription(), x.getThumbnail()))
                .collect(Collectors.toList())
        );
    }

    @GetMapping(Routes.GET_HIGHLIGHTED_PRODUCTS)
    public ResponseEntity<Collection<ProductDTO>> getHighlightedProducts() {
        return ResponseEntity.ok(productService
                .findByHighlighted()
                .stream()
                .map(x -> new ProductDTO(x.getId(), x.getName(), x.getPrice(), x.getQuantity(), x.getDescription(), x.getThumbnail()))
                .collect(Collectors.toList())
        );
    }

}
