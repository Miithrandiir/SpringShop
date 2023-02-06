package fr.ulco.springshop.service.core;

import fr.ulco.springshop.model.bo.ProductBO;

import java.util.Collection;
import java.util.Optional;

public interface ProductServiceInterface {

    Collection<ProductBO> findAll();
    Optional<ProductBO> findById(int id);

    Collection<ProductBO> findByCategory(String slugCategory);

}
