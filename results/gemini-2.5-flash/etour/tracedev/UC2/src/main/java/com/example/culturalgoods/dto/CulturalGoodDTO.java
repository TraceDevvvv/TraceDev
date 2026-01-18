package com.example.culturalgoods.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Data Transfer Object for CulturalGood.
 * Used to transfer cultural good data between layers, particularly for input and display.
 */
public class CulturalGoodDTO {
    public String name; // Name of the cultural good
    public String description; // Description of the cultural good
    public LocalDate acquisitionDate; // Acquisition date of the cultural good

    /**
     * Constructs a new CulturalGoodDTO.
     * @param name The name of the cultural good.
     * @param description The description of the cultural good.
     * @param acquisitionDate The acquisition date of the cultural good.
     */
    public CulturalGoodDTO(String name, String description, LocalDate acquisitionDate) {
        this.name = name;
        this.description = description;
        this.acquisitionDate = acquisitionDate;
    }

    // Getters for accessing DTO fields (though public fields are specified, getters are good practice)
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    @Override
    public String toString() {
        return "CulturalGoodDTO{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", acquisitionDate=" + acquisitionDate +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CulturalGoodDTO that = (CulturalGoodDTO) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(description, that.description) &&
               Objects.equals(acquisitionDate, that.acquisitionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, acquisitionDate);
    }
}