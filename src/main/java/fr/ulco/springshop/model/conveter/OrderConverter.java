package fr.ulco.springshop.model.conveter;

import fr.ulco.springshop.model.DTOEntityConverter;
import fr.ulco.springshop.model.dto.OrderDTO;
import fr.ulco.springshop.model.dto.OrderItemDTO;
import fr.ulco.springshop.model.entities.OrderEntity;
import fr.ulco.springshop.model.entities.OrderItemEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConverter extends DTOEntityConverter<OrderEntity, OrderDTO> {

    private final ProductConverter productConverter;

    public static OrderConverter create(ProductConverter productConverter) {
        return new OrderConverter(productConverter);
    }

    @Override
    public OrderEntity convertToEntity(OrderDTO orderDTO) {
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
    public OrderDTO convertToDTO(OrderEntity orderEntity) {
        OrderDTO o = new OrderDTO();
        o.setId(orderEntity.getId());
        o.setCreatedAt(orderEntity.getCreatedAt());

        Collection<OrderItemDTO> items = new ArrayList<>();
        orderEntity.getItems().forEach(x -> {
            OrderItemDTO item = new OrderItemDTO();
            item.setId(x.getId());
            item.setQuantity(x.getQuantity());
            item.setOrder(o);

            item.setProduct(productConverter.convertToDTO(x.getProduct()));


            items.add(item);

        });

        o.setItems(items);

        return o;
    }
}
