package com.atastaff.absencesystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * JPA Entity for Absence, representing a student's absence record.
 * This class maps to an 'absences' table in the database.
 */
@Entity
@Table(name = "absences")
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class Absence {

    /**
     * Unique identifier for the absence record.
     * Marked as the primary key.
     * Generated automatically using UUID for uniqueness.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Use UUID for absenceId
    private String absenceId;

    /**
     * The student who is absent.
     * Many-to-one relationship: many absences can belong to one student.
     * The 'student_id' column in the 'absences' table will store the foreign key.
     */
    @ManyToOne(fetch = FetchType.LAZY) // Lazy fetching to avoid loading the student eagerly
    @JoinColumn(name = "student_id", nullable = false) // Specifies the foreign key column
    private Student student;

    /**
     * The class for which the student is absent.
     * Many-to-one relationship: many absences can be recorded for one class.
     * The 'class_id' column in the 'absences' table will store the foreign key.
     */
    @ManyToOne(fetch = FetchType.LAZY) // Lazy fetching to avoid loading the class eagerly
    @JoinColumn(name = "class_id", nullable = false) // Specifies the foreign key column
    private Class classEntity; // Renamed to classEntity to avoid conflict with Java's Class keyword

    /**
     * The date on which the absence occurred.
     */
    @Column(nullable = false)
    private LocalDate absenceDate;

    /**
     * A flag indicating whether the parent has been notified about this absence.
     */
    @Column(nullable = false)
    private boolean notifiedParent;
}