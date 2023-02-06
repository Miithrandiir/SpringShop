package fr.ulco.springshop.model.dao;

import fr.ulco.springshop.model.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByCategories_Slug(@NonNull String slug);


}
