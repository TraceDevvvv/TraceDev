package com.atastaff.absencesystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA Entity for Student, representing a student in the system.
 * This class maps to a 'students' table in the database.
 */
@Entity
@Table(name = "students")
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class Student {

    /**
     * Unique identifier for the student.
     * Marked as the primary key.
     */
    @Id
    private String studentId;

    /**
     * The first name of the student.
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * The last name of the student.
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * The email address of the student's parent, used for sending absence notifications.
     */
    @Column(nullable = false)
    private String parentEmail;

    /**
     * The class to which the student currently belongs.
     * Many-to-one relationship: many students can belong to one class.
     * The 'class_id' column in the 'students' table will store the foreign key.
     */
    @ManyToOne(fetch = FetchType.LAZY) // Lazy fetching to avoid loading the class eagerly
    @JoinColumn(name = "class_id", nullable = false) // Specifies the foreign key column
    private Class currentClass;
}