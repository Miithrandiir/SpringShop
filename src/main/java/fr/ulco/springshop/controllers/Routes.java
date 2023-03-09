package fr.ulco.springshop.controllers;

public class Routes {

    /**
     * ROUTE CATEGORIES
     */
    public static final String GET_CATEGORIES = "/categories";
    public static final String GET_CATEGORY_BY_SLUG = "/categories/{slug}";
    public static final String POST_CATEGORIES = GET_CATEGORIES;
    public static final String DELETE_CATEGORY_BY_SLUG = GET_CATEGORY_BY_SLUG;
    public static final String UPDATE_CATEGORY_BY_SLUG = GET_CATEGORY_BY_SLUG;

    /**
     * ROUTE PRODUCTS
     */
    public static final String GET_PRODUCTS = "/products";
    public static final String GET_PRODUCT_BY_ID = "/products/{id}";
    public static final String GET_PRODUCTS_THUMBNAIL = GET_PRODUCT_BY_ID + "/thumbnail";
    public static final String GET_PRODUCTS_BY_CATEGORY = "/products/category/{slug}";
    public static final String GET_HIGHLIGHTED_PRODUCTS = "/products/highlighted";
    public static final String POST_PRODUCTS = GET_PRODUCTS;

    /**
     * ROUTE ORDERS
     */
    public static final String GET_ORDERS = "/orders";
    public static final String POST_ORDER = GET_ORDERS;

    /**
     * ROUTE LOGIN
     */
    public static final String LOGIN = "/api/auth";
    public static final String REGISTER = "/users/register";

}
