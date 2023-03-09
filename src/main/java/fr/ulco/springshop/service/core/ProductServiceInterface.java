package fr.ulco.springshop.service.core;

import fr.ulco.springshop.model.bo.ProductBO;

import java.util.Collection;
import java.util.Optional;

public interface ProductServiceInterface {

    /**
     * Find all products
     * @return list of products
     */
    Collection<ProductBO> findAll();

    /**
     * Find product by id
     * @param id id of product
     * @return product
     */
    Optional<ProductBO> findById(int id);

    /**
     * Find products by category
     * @param slugCategory slug of category
     * @return list of products
     */
    Collection<ProductBO> findByCategory(String slugCategory);

    /**
     * Find highlighted products
     * @return list of products
     */
    Collection<ProductBO> findByHighlighted();

    /**
     * Save product
     * @param productBO product to save
     * @return product saved
     */
    ProductBO save(ProductBO productBO);

    /**
     * Update product
     * @param productBO product to update
     * @return product updated
     */
    ProductBO update(ProductBO productBO);

}
