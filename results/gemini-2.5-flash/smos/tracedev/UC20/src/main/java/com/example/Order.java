package com.example;

import java.util.Date;

/**
 * Represents an Order entity in the domain layer,
 * referencing an Address by its ID.
 */
public class Order {
    private String id;
    private String addressId; // References an Address by its ID
    private Date orderDate;

    /**
     * Constructs a new Order object.
     *
     * @param id The unique identifier of the order.
     * @param addressId The ID of the address associated with this order.
     * @param orderDate The date when the order was placed.
     */
    public Order(String id, String addressId, Date orderDate) {
        this.id = id;
        this.addressId = addressId;
        this.orderDate = orderDate;
    }

    // Getters for all attributes
    public String getId() {
        return id;
    }

    public String getAddressId() {
        return addressId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
               "id='" + id + '\'' +
               ", addressId='" + addressId + '\'' +
               ", orderDate=" + orderDate +
               '}';
    }
}