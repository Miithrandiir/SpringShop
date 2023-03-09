package fr.ulco.springshop.service.core;

import fr.ulco.springshop.model.bo.OrderBO;
import fr.ulco.springshop.model.exception.OutOfStockException;

import java.util.Collection;

public interface OrderServiceInterface {

    /**
     * Find orders by user
     * @param idUser id of user
     * @return list of orders
     */
    Collection<OrderBO> findByUser(int idUser);

    /**
     * Save order
     * @param orderBO order to save
     * @return order saved
     */
    public OrderBO save(OrderBO orderBO) throws OutOfStockException;
}
