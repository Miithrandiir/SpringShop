package fr.ulco.springshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryDTO {
    private int id;
    private String name;
    private String slug;

    public CategoryDTO(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }
}
