package fr.ulco.springshop.controllers;

import fr.ulco.springshop.model.bo.CategoryBO;
import fr.ulco.springshop.service.core.CategoryServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceInterface categoryService;

    @GetMapping(Routes.GET_CATEGORIES)
    public ResponseEntity<Collection<CategoryBO>> getCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }


}
