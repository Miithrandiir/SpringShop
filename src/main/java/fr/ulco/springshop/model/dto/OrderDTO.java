package fr.ulco.springshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private int id;

    private LocalDateTime createdAt;

    private UserDTO user;

    private Collection<OrderItemDTO> items;
}
