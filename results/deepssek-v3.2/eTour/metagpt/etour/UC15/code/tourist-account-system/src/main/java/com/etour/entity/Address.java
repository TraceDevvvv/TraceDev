package com.etour.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Embeddable entity representing an address.
 * Can be embedded in other entities like Tourist.
 * Maps address fields in a reusable way.
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    
    @Column(name = "street", length = 255)
    private String street;
    
    @Column(name = "city", length = 100)
    private String city;
    
    @Column(name = "state", length = 100)
    private String state;
    
    @Column(name = "postal_code", length = 20)
    private String postalCode;
    
    @Column(name = "country", length = 100)
    private String country;
    
    /**
     * Returns the complete formatted address.
     * 
     * @return Full address string in standard format
     */
    public String getFullAddress() {
        StringBuilder addressBuilder = new StringBuilder();
        
        if (street != null && !street.trim().isEmpty()) {
            addressBuilder.append(street);
        }
        
        if (city != null && !city.trim().isEmpty()) {
            if (addressBuilder.length() > 0) {
                addressBuilder.append(", ");
            }
            addressBuilder.append(city);
        }
        
        if (state != null && !state.trim().isEmpty()) {
            if (addressBuilder.length() > 0) {
                addressBuilder.append(", ");
            }
            addressBuilder.append(state);
        }
        
        if (postalCode != null && !postalCode.trim().isEmpty()) {
            if (addressBuilder.length() > 0) {
                addressBuilder.append(" ");
            }
            addressBuilder.append(postalCode);
        }
        
        if (country != null && !country.trim().isEmpty()) {
            if (addressBuilder.length() > 0) {
                addressBuilder.append(", ");
            }
            addressBuilder.append(country);
        }
        
        return addressBuilder.toString();
    }
    
    /**
     * Checks if the address is empty (all fields null or empty).
     * 
     * @return true if address is empty, false otherwise
     */
    public boolean isEmpty() {
        return (street == null || street.trim().isEmpty()) &&
               (city == null || city.trim().isEmpty()) &&
               (state == null || state.trim().isEmpty()) &&
               (postalCode == null || postalCode.trim().isEmpty()) &&
               (country == null || country.trim().isEmpty());
    }
    
    /**
     * Validates the address for basic correctness.
     * 
     * @return true if address is valid, false otherwise
     */
    public boolean isValid() {
        // At minimum, city and country should be provided
        if (city == null || city.trim().isEmpty()) {
            return false;
        }
        if (country == null || country.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}