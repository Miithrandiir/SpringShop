package fr.ulco.springshop.service;

import fr.ulco.springshop.model.bo.OrderBO;
import fr.ulco.springshop.model.bo.OrderItemBO;
import fr.ulco.springshop.model.bo.ProductBO;
import fr.ulco.springshop.model.conveter.OrderConverter;
import fr.ulco.springshop.model.dao.OrderItemRepository;
import fr.ulco.springshop.model.dao.OrderRepository;
import fr.ulco.springshop.model.entities.OrderEntity;
import fr.ulco.springshop.model.exception.OutOfStockException;
import fr.ulco.springshop.service.core.OrderServiceInterface;
import fr.ulco.springshop.service.core.ProductServiceInterface;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderService implements OrderServiceInterface {

    public static OrderServiceInterface create(final OrderRepository orderRepository, final OrderConverter orderConverter, final OrderItemRepository orderItemRepository, final ProductServiceInterface productService) {
        return new OrderService(orderRepository, orderConverter, orderItemRepository, productService);
    }

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    private final OrderItemRepository orderItemRepository;
    private final ProductServiceInterface productService;

    @Override
    public Collection<OrderBO> findByUser(int idUser) {
        return orderRepository.findByUser_IdOrderByCreatedAtDesc(idUser).stream().map(orderConverter::convertToBO).toList();
    }

    @Override
    public OrderBO save(OrderBO orderBO) throws OutOfStockException {
        // On vérifie si le stock est bon
        List<ProductBO> outStock = new ArrayList<>();

        orderBO.getItems().forEach(orderItemBO -> {
            if (orderItemBO.getQuantity() > orderItemBO.getProduct().getQuantity()) {
                outStock.add(orderItemBO.getProduct());
            }
        });

        if (!outStock.isEmpty()) {
            throw new OutOfStockException(outStock);
        }


        OrderEntity orderEntity = orderRepository.saveAndFlush(orderConverter.convertToEntity(orderBO));
        orderEntity.setItems(orderEntity.getItems().stream().map(orderItemRepository::save).toList());

        List<OrderItemBO> items = orderBO.getItems().stream().map(orderItemBO -> {
            ProductBO productBO = orderItemBO.getProduct();

            productBO.setQuantity(productBO.getQuantity() - orderItemBO.getQuantity());
            orderItemBO.setProduct(productService.save(productBO));
            return orderItemBO;
        }).toList();

        orderBO.setItems(items);


        return orderConverter.convertToBO(orderEntity);
    }
}
