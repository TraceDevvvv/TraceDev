package com.example.dto;

import com.example.model.Refreshment;
import java.util.Date;

/**
 * Data Transfer Object for Refreshment entity.
 * Maps to and from Refreshment entity.
 */
public class RefreshmentDTO {
    private String id;
    private String name;
    private String description;
    private double price;
    private int availableQuantity;

    /**
     * Constructor to create DTO from Refreshment entity.
     */
    public RefreshmentDTO(Refreshment refreshment) {
        if (refreshment != null) {
            this.id = refreshment.getId();
            this.name = refreshment.getName();
            this.description = refreshment.getDescription();
            this.price = refreshment.getPrice();
            this.availableQuantity = refreshment.getAvailableQuantity();
        }
    }

    /**
     * Converts this DTO to a Refreshment entity.
     */
    public Refreshment toEntity() {
        Refreshment refreshment = new Refreshment();
        refreshment.setId(this.id);
        refreshment.setName(this.name);
        refreshment.setDescription(this.description);
        refreshment.setPrice(this.price);
        refreshment.setAvailableQuantity(this.availableQuantity);
        refreshment.setLastUpdated(new Date());
        return refreshment;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}