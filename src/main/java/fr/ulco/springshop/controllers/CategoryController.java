package fr.ulco.springshop.controllers;

import fr.ulco.springshop.model.bo.CategoryBO;
import fr.ulco.springshop.model.dto.CategoryDTO;
import fr.ulco.springshop.model.form.CategoryForm;
import fr.ulco.springshop.service.core.CategoryServiceInterface;
import fr.ulco.springshop.service.core.SluggerServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceInterface categoryService;
    private final SluggerServiceInterface sluggerService;

    @GetMapping(Routes.GET_CATEGORIES)
    public ResponseEntity<Collection<CategoryDTO>> getCategories() {
        return ResponseEntity.ok(categoryService
                .findAll()
                .stream()
                .map(x -> new CategoryDTO(x.getName(), x.getSlug()))
                .collect(Collectors.toList())
        );
    }

    @GetMapping(Routes.GET_CATEGORY_BY_SLUG)
    public ResponseEntity<CategoryDTO> getCategoryBySlug(@PathVariable String slug) {
        return categoryService
                .findBySlug(slug)
                .map(x -> new CategoryDTO(x.getName(), x.getSlug()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ADMIN
    @PostMapping(value = Routes.POST_CATEGORIES, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryDTO> postCategories(@ModelAttribute CategoryForm categoryDTO) {
        return categoryService.save(
                        new CategoryBO(
                                categoryDTO.getName(),
                                sluggerService.toSlug(categoryDTO.getName())
                        )
                )
                .map(x -> new CategoryDTO(x.getId(), x.getName(), x.getSlug()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ADMIN
    @DeleteMapping(value = Routes.DELETE_CATEGORY_BY_SLUG)
    public ResponseEntity<Boolean> deleteCategoryBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(categoryService.deleteBySlug(slug));
    }

    //ADMIN
    @PutMapping(value = Routes.UPDATE_CATEGORY_BY_SLUG, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryDTO> putCategoryBySlug(@PathVariable String slug, @ModelAttribute CategoryForm categoryDTO) {

        Optional<CategoryBO> res = categoryService.updateBySlug(
                slug,
                new CategoryBO(
                        categoryDTO.getName(),
                        sluggerService.toSlug(categoryDTO.getName())
                )
        );


        return res
                .map(x -> new CategoryDTO(x.getId(), x.getName(), x.getSlug()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
}
