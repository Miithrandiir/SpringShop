package fr.ulco.springshop.service.core;

import fr.ulco.springshop.model.dto.CategoryDTO;

import java.util.Collection;

public interface CategoryServiceInterface {

    public Collection<CategoryDTO> findAll();

    public CategoryDTO create(String name);

    public CategoryDTO update(CategoryDTO categoryDTO);


}
