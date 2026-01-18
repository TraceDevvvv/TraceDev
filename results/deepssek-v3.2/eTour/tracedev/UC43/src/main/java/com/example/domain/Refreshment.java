package com.example.domain;

import com.example.dto.RefreshmentDTO;
import java.math.BigDecimal;

/**
 * Domain entity representing a refreshment item.
 */
public class Refreshment {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;

    public Refreshment() {}

    public Refreshment(Long id, String name, String description, BigDecimal price, Boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * Updates this entity's fields from a DTO (added to satisfy requirement REQ-FLOW-009).
     * @param dto the DTO containing new values
     */
    public void updateFromDTO(RefreshmentDTO dto) {
        this.setName(dto.getName());
        this.setDescription(dto.getDescription());
        this.setPrice(dto.getPrice());
        this.setAvailable(dto.isAvailable());
    }
}