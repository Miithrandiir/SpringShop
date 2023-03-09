package fr.ulco.springshop.service;

import fr.ulco.springshop.model.bo.OrderBO;
import fr.ulco.springshop.model.conveter.OrderConverter;
import fr.ulco.springshop.model.dao.OrderRepository;
import fr.ulco.springshop.service.core.OrderServiceInterface;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderService implements OrderServiceInterface {

    public static OrderServiceInterface create(final OrderRepository orderRepository, final OrderConverter orderConverter) {
        return new OrderService(orderRepository, orderConverter);
    }

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @Override
    public Collection<OrderBO> findByUser(int idUser) {
        return orderRepository.findByUser_IdOrderByCreatedAtDesc(idUser).stream().map(orderConverter::convertToBO).toList();
    }
}
