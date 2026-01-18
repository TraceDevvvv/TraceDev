package com.etour.dto;

import com.etour.entity.Address;
import com.etour.entity.Tourist;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Tourist entity.
 * Used for API responses and data transfer between layers.
 * Includes validation annotations for input validation.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TouristDTO {
    
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters")
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;
    
    @Pattern(regexp = "^\\+?[0-9\\-\\(\\)\\s]*$", message = "Phone number format is invalid")
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phoneNumber;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    
    private Address address;
    
    @Size(max = 100, message = "Nationality must not exceed 100 characters")
    private String nationality;
    
    @Size(max = 50, message = "Passport number must not exceed 50 characters")
    private String passportNumber;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    
    private Boolean active;
    
    private Integer version;
    
    /**
     * Creates a TouristDTO from a Tourist entity.
     * 
     * @param tourist The Tourist entity to convert
     * @return TouristDTO with data from the entity
     */
    public static TouristDTO fromEntity(Tourist tourist) {
        if (tourist == null) {
            return null;
        }
        
        return TouristDTO.builder()
                .id(tourist.getId())
                .firstName(tourist.getFirstName())
                .lastName(tourist.getLastName())
                .email(tourist.getEmail())
                .phoneNumber(tourist.getPhoneNumber())
                .dateOfBirth(tourist.getDateOfBirth())
                .address(tourist.getAddress())
                .nationality(tourist.getNationality())
                .passportNumber(tourist.getPassportNumber())
                .createdAt(tourist.getCreatedAt())
                .updatedAt(tourist.getUpdatedAt())
                .active(tourist.getActive())
                .version(tourist.getVersion())
                .build();
    }
    
    /**
     * Converts this DTO to a Tourist entity.
     * Note: Does not set id, createdAt, updatedAt, or version as these are managed by JPA.
     * 
     * @return Tourist entity with data from this DTO
     */
    public Tourist toEntity() {
        return Tourist.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .dateOfBirth(this.dateOfBirth)
                .address(this.address)
                .nationality(this.nationality)
                .passportNumber(this.passportNumber)
                .active(this.active != null ? this.active : true)
                .build();
    }
    
    /**
     * Returns the full name of the tourist.
     * 
     * @return Full name as "firstName lastName"
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Checks if the DTO represents a valid tourist.
     * 
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        return firstName != null && !firstName.trim().isEmpty() &&
               lastName != null && !lastName.trim().isEmpty() &&
               email != null && email.contains("@");
    }
    
    /**
     * Gets a summary string for logging/debugging.
     * 
     * @return Summary string
     */
    public String getSummary() {
        return String.format("TouristDTO[id=%d, name=%s %s, email=%s]", 
                id, firstName, lastName, email);
    }
}