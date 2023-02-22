package fr.ulco.springshop.controllers;

public class Routes {

    /**
     * ROUTE CATEGORIES
     */
    public static final String GET_CATEGORIES = "/categories";
    public static final String GET_CATEGORY_BY_SLUG = "/categories/{slug}";

    /**
     * ROUTE PRODUCTS
     */
    public static final String GET_PRODUCTS = "/products";
    public static final String GET_PRODUCT_BY_ID = "/products/{id}";
    public static final String GET_PRODUCTS_THUMBNAIL = GET_PRODUCT_BY_ID + "/thumbnail";
    public static final String GET_PRODUCTS_BY_CATEGORY = "/products/category/{slug}";
    public static final String GET_HIGHLIGHTED_PRODUCTS = "/products/highlighted";
    public static final String POST_PRODUCTS = GET_PRODUCTS;
}
