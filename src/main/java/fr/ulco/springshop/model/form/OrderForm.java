package fr.ulco.springshop.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
public class OrderForm {
    private Collection<OrderItemForm> items;

    private OrderForm() {
        this.items = new ArrayList<>();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class OrderItemForm {
        private String product;
        private int quantity;
    }
}
