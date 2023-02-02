package fr.ulco.springshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private int id;

    private OrderDTO order;
    private ProductDTO product;
    private int quantity;
}
