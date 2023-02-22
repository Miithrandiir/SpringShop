package fr.ulco.springshop.model.dao;

import fr.ulco.springshop.model.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findBySlug(String slug);
}
