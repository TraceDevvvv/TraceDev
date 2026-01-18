package com.example.insertdelayadmin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Represents a Student entity in the system.
 * Students can have multiple delay records associated with them.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    /**
     * Unique identifier for the student.
     */
    @Id
    private String id;

    /**
     * The name of the student.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Contact information for the student's parent or guardian.
     * This could be an email address or phone number for notifications.
     */
    private String parentContactInfo;

    /**
     * A list of delay records associated with this student.
     * This establishes a one-to-many relationship where one student can have multiple delay records.
     * CascadeType.ALL ensures that if a student is deleted, their associated delay records are also deleted.
     * FetchType.LAZY means the delay records are loaded only when accessed, improving performance.
     */
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DelayRecord> delayRecords;
}