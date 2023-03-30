package fr.ulco.springshop.model.exception;

import fr.ulco.springshop.model.bo.ProductBO;

import java.util.Collection;

public class OutOfStockException extends Exception {

    Collection<ProductBO> products;

    public OutOfStockException(Collection<ProductBO> products) {
        super("Out of stock");
        this.products = products;
    }

    public Collection<ProductBO> getProducts() {
        return products;
    }
}
