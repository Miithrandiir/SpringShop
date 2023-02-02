package fr.ulco.springshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryDTO {
    private String name;
    private String slug;
}
