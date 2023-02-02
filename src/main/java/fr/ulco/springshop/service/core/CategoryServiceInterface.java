package fr.ulco.springshop.service.core;

import fr.ulco.springshop.model.bo.CategoryBO;

import java.util.Collection;

public interface CategoryServiceInterface {

    public Collection<CategoryBO> findAll();

    public CategoryBO create(String name);

    public CategoryBO update(CategoryBO categoryDTO);


}
