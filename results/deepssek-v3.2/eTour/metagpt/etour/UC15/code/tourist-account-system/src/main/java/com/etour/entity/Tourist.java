package com.etour.entity;

import com.etour.dto.TouristUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity class representing a Tourist account in the system.
 * Maps to the 'tourists' table in the database.
 * Implements optimistic locking through @Version annotation.
 */
@Entity
@Table(name = "tourists")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tourist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;
    
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Embedded
    private Address address;
    
    @Column(name = "nationality", length = 100)
    private String nationality;
    
    @Column(name = "passport_number", unique = true, length = 50)
    private String passportNumber;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Version
    @Column(name = "version")
    private Integer version;
    
    @Column(name = "is_active")
    private Boolean active;
    
    /**
     * Returns the full name of the tourist by concatenating first and last name.
     * 
     * @return Full name as "firstName lastName"
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Updates the tourist entity with data from the update request.
     * Only updates fields that are not null in the request.
     * 
     * @param request The update request containing new values
     */
    public void updateFrom(TouristUpdateRequest request) {
        if (request.getFirstName() != null) {
            this.firstName = request.getFirstName();
        }
        if (request.getLastName() != null) {
            this.lastName = request.getLastName();
        }
        if (request.getEmail() != null) {
            this.email = request.getEmail();
        }
        if (request.getPhoneNumber() != null) {
            this.phoneNumber = request.getPhoneNumber();
        }
        if (request.getDateOfBirth() != null) {
            this.dateOfBirth = request.getDateOfBirth();
        }
        if (request.getAddress() != null) {
            this.address = request.getAddress();
        }
        if (request.getNationality() != null) {
            this.nationality = request.getNationality();
        }
        if (request.getPassportNumber() != null) {
            this.passportNumber = request.getPassportNumber();
        }
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * Validates the tourist entity for business rules.
     * 
     * @return true if the tourist data is valid, false otherwise
     */
    public boolean validate() {
        // Basic validation rules
        if (firstName == null || firstName.trim().isEmpty()) {
            return false;
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            return false;
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            return false;
        }
        // Date of birth should not be in the future
        if (dateOfBirth != null && dateOfBirth.isAfter(LocalDate.now())) {
            return false;
        }
        // Active status should not be null
        if (active == null) {
            this.active = true; // Default to active
        }
        return true;
    }
    
    /**
     * Pre-update hook to ensure validation before saving.
     */
    @PreUpdate
    public void preUpdate() {
        // Ensure data is valid before updating
        if (!validate()) {
            throw new IllegalStateException("Tourist data is invalid before update");
        }
    }
    
    /**
     * Pre-persist hook to set default values and validate.
     */
    @PrePersist
    public void prePersist() {
        if (active == null) {
            active = true;
        }
        validate();
    }
}