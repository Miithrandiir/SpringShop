package fr.ulco.springshop.controllers;

import fr.ulco.springshop.model.bo.ProductBO;
import fr.ulco.springshop.model.dto.ProductDTO;
import fr.ulco.springshop.model.form.ProductForm;
import fr.ulco.springshop.service.core.ProductServiceInterface;
import fr.ulco.springshop.service.core.StorageServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceInterface productService;
    private final StorageServiceInterface storageService;

    @GetMapping(Routes.GET_PRODUCTS)
    public ResponseEntity<Collection<ProductDTO>> getProducts() {

        return ResponseEntity.ok(productService
                .findAll()
                .stream()
                .map(x -> new ProductDTO(x.getId(), x.getName(), x.getPrice(), x.getQuantity(), x.getDescription(), getRouteProductThumbnail(x)))
                .collect(Collectors.toList())
        );
    }

    @GetMapping(Routes.GET_PRODUCTS_THUMBNAIL)
    public ResponseEntity<Resource> getProductsThumbnail(@PathVariable int id) {
        Optional<ProductBO> productBO = productService.findById(id);
        if (productBO.isPresent() && productBO.get().getThumbnail() != null) {

            Resource resource = storageService.loadAsResource(productBO.get().getThumbnail());
            return
                    ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                                    "attachment; filename=\"" + productBO.get().getThumbnail() + "\"")
                            .body(resource);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(Routes.GET_PRODUCTS_BY_CATEGORY)
    public ResponseEntity<Collection<ProductDTO>> getProductsByCategory(@PathVariable String slug) {
        return ResponseEntity.ok(productService
                .findByCategory(slug)
                .stream()
                .map(x -> new ProductDTO(x.getId(), x.getName(), x.getPrice(), x.getQuantity(), x.getDescription(), getRouteProductThumbnail(x)))
                .collect(Collectors.toList())
        );
    }

    @GetMapping(Routes.GET_HIGHLIGHTED_PRODUCTS)
    public ResponseEntity<Collection<ProductDTO>> getHighlightedProducts() {
        return ResponseEntity.ok(productService
                .findByHighlighted()
                .stream()
                .map(x -> new ProductDTO(x.getId(), x.getName(), x.getPrice(), x.getQuantity(), x.getDescription(), getRouteProductThumbnail(x)))
                .collect(Collectors.toList())
        );
    }

    @GetMapping(Routes.GET_PRODUCT_BY_ID)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService
                .findById(id)
                .map(x -> new ProductDTO(x.getId(), x.getName(), x.getPrice(), x.getQuantity(), x.getDescription(), getRouteProductThumbnail(x)))
                .orElse(null));
    }


    @PostMapping(value = Routes.POST_PRODUCTS, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDTO> postProduct(@ModelAttribute ProductForm productForm) {

        String thumbnail = this.storageService.store(productForm.getThumbnail());

        ProductBO createdBo = productService
                .save(new ProductBO(productForm.getName(), productForm.getPrice(), productForm.getQuantity(), productForm.getDescription(), thumbnail));

        return ResponseEntity.ok(new ProductDTO(createdBo.getId(), createdBo.getName(), createdBo.getPrice(), createdBo.getQuantity(), createdBo.getDescription(), getRouteProductThumbnail(createdBo)));
    }


    private String getRouteProductThumbnail(ProductBO productBO) {
        if (productBO.getThumbnail() == null)
            return null;
        return Routes.GET_PRODUCTS_THUMBNAIL.replace("{id}", String.valueOf(productBO.getId()));
    }

}
