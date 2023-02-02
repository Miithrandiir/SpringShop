package fr.ulco.springshop.model.dao;

import fr.ulco.springshop.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
