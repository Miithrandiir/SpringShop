package fr.ulco.springshop.model.dao;

import fr.ulco.springshop.model.entities.OrderEntity;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    public Collection<OrderEntity> findByUser_IdOrderByCreatedAtDesc(int idUser);
}
