package com.redis_project.springboot.redis.common;

public class Urls {
    // customers
    public static final String CUSTOMERS_BASE = "/customers";
    public static final String CREATE_CUSTOMER = CUSTOMERS_BASE + "/create";
    public static final String UPDATE_CUSTOMER = CUSTOMERS_BASE + "/update/{id}";
    public static final String GET_CUSTOMER_BYID = CUSTOMERS_BASE + "/{id}";
    public static final String DELETE_CUSTOMER = CUSTOMERS_BASE + "/{id}";
}
