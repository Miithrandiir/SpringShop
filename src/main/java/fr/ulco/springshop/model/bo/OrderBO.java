package fr.ulco.springshop.model.bo;

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
public class OrderBO {

    private int id;

    private LocalDateTime createdAt;

    private UserBO user;

    private Collection<OrderItemBO> items;
}
