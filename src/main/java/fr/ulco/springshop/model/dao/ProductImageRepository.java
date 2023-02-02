package fr.ulco.springshop.model.dao;

import fr.ulco.springshop.model.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductEntity, Integer> {
}
