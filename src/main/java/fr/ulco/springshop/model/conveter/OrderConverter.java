package fr.ulco.springshop.model.conveter;

import fr.ulco.springshop.model.AbstractBOEntityConverter;
import fr.ulco.springshop.model.bo.OrderBO;
import fr.ulco.springshop.model.bo.OrderItemBO;
import fr.ulco.springshop.model.entities.OrderEntity;
import fr.ulco.springshop.model.entities.OrderItemEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConverter extends AbstractBOEntityConverter<OrderEntity, OrderBO> {

    private final ProductConverter productConverter;
    private final UserConverter userConverter;

    public static OrderConverter create(ProductConverter productConverter, UserConverter userConverter) {
        return new OrderConverter(productConverter, userConverter);
    }

    @Override
    public OrderEntity convertToEntity(OrderBO orderDTO) {
        OrderEntity o = new OrderEntity();
        o.setId(orderDTO.getId());
        o.setCreatedAt(orderDTO.getCreatedAt());

        Collection<OrderItemEntity> items = new ArrayList<>();
        orderDTO.getItems().forEach(x -> {
            OrderItemEntity item = new OrderItemEntity();
            item.setId(x.getId());
            item.setQuantity(x.getQuantity());
            item.setOrder(o);

            item.setProduct(productConverter.convertToEntity(x.getProduct()));


            items.add(item);

        });

        o.setItems(items);

        return o;
    }

    @Override
    public OrderBO convertToBO(OrderEntity orderEntity) {
        OrderBO o = new OrderBO();
        o.setId(orderEntity.getId());
        o.setCreatedAt(orderEntity.getCreatedAt());
        o.setUser(userConverter.convertToBO(orderEntity.getUser()));

        Collection<OrderItemBO> items = new ArrayList<>();
        orderEntity.getItems().forEach(x -> {
            OrderItemBO item = new OrderItemBO();
            item.setId(x.getId());
            item.setQuantity(x.getQuantity());
            item.setOrder(o);

            item.setProduct(productConverter.convertToBO(x.getProduct()));


            items.add(item);

        });

        o.setItems(items);

        return o;
    }
}
