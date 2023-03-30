package fr.ulco.springshop.controllers;

import fr.ulco.springshop.model.bo.CategoryBO;
import fr.ulco.springshop.model.bo.ProductBO;
import fr.ulco.springshop.model.dto.ProductDTO;
import fr.ulco.springshop.model.form.ProductForm;
import fr.ulco.springshop.service.CategoryService;
import fr.ulco.springshop.service.core.ProductServiceInterface;
import fr.ulco.springshop.service.core.StorageServiceInterface;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "Products", description = "The products API")
public class ProductController {

    private final ProductServiceInterface productService;
    private final StorageServiceInterface storageService;

    private final CategoryService categoryService;

    @GetMapping(Routes.GET_PRODUCTS)
    public ResponseEntity<Collection<ProductDTO>> getProducts() {

        return ResponseEntity.ok(productService
                .findAll()
                .stream()
                .map(ProductDTO::new)
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
                .map(ProductDTO::new)
                .collect(Collectors.toList())
        );
    }

    @GetMapping(Routes.GET_HIGHLIGHTED_PRODUCTS)
    public ResponseEntity<Collection<ProductDTO>> getHighlightedProducts() {
        return ResponseEntity.ok(productService
                .findByHighlighted()
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList())
        );
    }

    @GetMapping(Routes.GET_PRODUCT_BY_ID)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
        return productService
                .findById(id)
                .map(ProductDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }


    @PostMapping(value = Routes.POST_PRODUCTS, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ProductDTO> postProduct(@ModelAttribute ProductForm productForm) {

        String thumbnail = this.storageService.store(productForm.getThumbnail());


        Collection<CategoryBO> categories = productForm.getCategories().stream()
                .map(CategoryController::getSlugFromRouteCategory)
                .map(categoryService::findBySlug)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        ProductBO createdBo = productService
                .save(new ProductBO(productForm.getName(), productForm.getPrice(), productForm.getQuantity(), productForm.getDescription(), thumbnail, categories));

        return ResponseEntity.ok(new ProductDTO(createdBo));
    }


    public static String getRouteProductThumbnail(ProductBO productBO) {
        if (productBO.getThumbnail() == null)
            return null;
        return Routes.GET_PRODUCTS_THUMBNAIL.replace("{id}", String.valueOf(productBO.getId()));
    }

}
