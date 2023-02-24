package fr.ulco.springshop.model.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryBO {
    private int id;
    private String name;
    private String slug;

    public CategoryBO(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }
}
