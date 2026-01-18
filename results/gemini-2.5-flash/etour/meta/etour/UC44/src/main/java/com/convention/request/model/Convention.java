package com.convention.request.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Represents a Convention agreement between a point of rest and an agency.
 * This entity stores all the details related to a convention request.
 */
@Entity
@Table(name = "conventions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Convention {

    @Id
    private String conventionId;

    @Column(nullable = false)
    private String agencyName;

    @Column(nullable = false)
    private String conventionType;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(length = 1000) // Assuming description can be long
    private String description;

    @Column(nullable = false)
    private String contactPerson;

    @Column(nullable = false)
    private String contactEmail;

    @ElementCollection // Stores a collection of basic types or embeddable classes
    @CollectionTable(name = "convention_required_documents", joinColumns = @JoinColumn(name = "convention_id"))
    @Column(name = "document_name")
    private List<String> requiredDocuments;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConventionStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Pre-persist method to set creation and update timestamps, and generate a UUID for conventionId.
     */
    @PrePersist
    protected void onCreate() {
        this.conventionId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = ConventionStatus.PENDING; // Default status
        }
    }

    /**
     * Pre-update method to set the update timestamp.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}