package fr.ulco.springshop.model.dao;

import fr.ulco.springshop.model.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
