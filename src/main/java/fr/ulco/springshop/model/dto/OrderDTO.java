package fr.ulco.springshop.model.dto;

import fr.ulco.springshop.controllers.Routes;
import fr.ulco.springshop.model.bo.OrderBO;
import fr.ulco.springshop.model.bo.OrderItemBO;
import org.springframework.data.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDTO {
    private final int id;
    private final LocalDateTime createdAt;
    private final String user;
    private final List<OderItemDto> items;

    private double price = 0.0;

    public OrderDTO(OrderBO orderBO) {
        this.id = orderBO.getId();
        this.createdAt = orderBO.getCreatedAt();
        this.user = orderBO.getUser().getUsername();
        this.items = new ArrayList<>();

        for (OrderItemBO orderItemBO : orderBO.getItems()) {
            this.items.add(new OderItemDto(Routes.GET_PRODUCT_BY_ID.replace("{id}", String.valueOf(orderItemBO.getProduct().getId())), orderItemBO.getQuantity()));
        }

        this.price = orderBO.getItems().stream().mapToDouble(orderItemBO -> orderItemBO.getProduct().getPrice() * orderItemBO.getQuantity()).sum();

    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUser() {
        return user;
    }

    public List<OderItemDto> getItems() {
        return items;
    }

    private class OderItemDto {
        private final String product;
        private final int quantity;

        public OderItemDto(String product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public String getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    public double getPrice() {
        return price;
    }
}
