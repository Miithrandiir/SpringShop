package fr.ulco.springshop.service.core;

import fr.ulco.springshop.model.bo.CategoryBO;

import java.util.Collection;
import java.util.Optional;

public interface CategoryServiceInterface {

    /**
     * Find all categories
     *
     * @return  Collection of CategoryBO
     */
    Collection<CategoryBO> findAll();

    /**
     * Create a new category
     *
     * @param name  Name of the category
     * @return      CategoryBO
     */
    CategoryBO create(String name);

    /**
     * Update a category
     *
     * @param categoryDTO   CategoryBO
     * @return              CategoryBO
     */
    CategoryBO update(CategoryBO categoryDTO);

    /**
     * Find a category by slug
     *
     * @param slug  Slug of the category
     * @return      CategoryBO
     */
    Optional<CategoryBO> findBySlug(String slug);

    /**
     * Save a category
     *
     * @param categoryBO    CategoryBO
     * @return              CategoryBO
     */
    Optional<CategoryBO> save(CategoryBO categoryBO);

    /**
     * Delete a category by slug
     * @param slug Slug of the category
     * @return boolean
     */
    boolean deleteBySlug(String slug);

    Optional<CategoryBO> updateBySlug(String slug, CategoryBO categoryBO);


}
