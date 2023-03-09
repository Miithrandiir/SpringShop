package fr.ulco.springshop.service;

import fr.ulco.springshop.model.bo.OrderBO;
import fr.ulco.springshop.model.conveter.OrderConverter;
import fr.ulco.springshop.model.dao.OrderItemRepository;
import fr.ulco.springshop.model.dao.OrderRepository;
import fr.ulco.springshop.model.entities.OrderEntity;
import fr.ulco.springshop.model.entities.OrderItemEntity;
import fr.ulco.springshop.service.core.OrderServiceInterface;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderService implements OrderServiceInterface {

    public static OrderServiceInterface create(final OrderRepository orderRepository, final OrderConverter orderConverter, final OrderItemRepository orderItemRepository) {
        return new OrderService(orderRepository, orderConverter, orderItemRepository);
    }

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Collection<OrderBO> findByUser(int idUser) {
        return orderRepository.findByUser_IdOrderByCreatedAtDesc(idUser).stream().map(orderConverter::convertToBO).toList();
    }

    @Override
    public OrderBO save(OrderBO orderBO) {
        OrderEntity orderEntity = orderRepository.save(orderConverter.convertToEntity(orderBO));
        orderEntity.setItems(orderEntity.getItems().stream().map(x -> orderItemRepository.save(x)).toList());


        return orderConverter.convertToBO(orderEntity);
    }
}
